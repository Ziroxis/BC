package com.yuanno.block_clover.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.mojang.serialization.Lifecycle;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncDynDimensionsPacket;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.listener.IChunkStatusListener;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.IServerConfiguration;
import net.minecraft.world.storage.SaveFormat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

/**
 * This whole class manages the dimensions
 *
 * Taken from Wynd -> Mine Mine no Mi mod
 */
public class DynamicDimensionManager
{
	public static final ChallengeDimensionRenderInfo CHALLANGE_DIM_INFO = new ChallengeDimensionRenderInfo();
	private static final Set<RegistryKey<World>> VANILLA_WORLDS = ImmutableSet.of(World.OVERWORLD, World.NETHER, World.END);

	private static Set<RegistryKey<World>> pendingWorldsToUnregister = new HashSet<>();

	/**
	 * Retrieves the World for a given World key, registering and creating one if it doesn't already exist.
	 * <p>
	 * Worlds created this way will be saved to the save folder's dimension registry
	 * and will be automatically loaded the next time the server starts. All registered Worlds will tick while they exist
	 * (though a World without any loaded chunks won't do much in the tick).
	 *
	 * @param server
	 *            a server
	 * @param worldKey
	 *            The resource key for the world
	 * @param dimensionFactory
	 *            The function to use to create the World's Dimension instance if it doesn't already exist;
	 *            the given Dimension key will have the same ID as the given World key
	 * @return A server World for the given key
	 * @apiNote To retreive static dimensions that always exist, or to look up a (nullable) world without force-creating it,
	 *          use {@link MinecraftServer#getWorld(RegistryKey)} instead.
	 *          <p>
	 *          To register a static dimension that always exists, make a json dimension instead; minecraft will load and register it automatically
	 *          <p>
	 *          To unregister a dynamic dimension World (preventing it from ticking, or from loading at server startup),
	 *          use {@link DynamicDimensionManager#markDimensionForUnregistration(MinecraftServer, RegistryKey)}.
	 */
	public static ServerWorld getOrCreateWorld(final MinecraftServer server, final RegistryKey<World> worldKey, final BiFunction<MinecraftServer, RegistryKey<Dimension>, Dimension> dimensionFactory)
	{
		// (we're doing the lookup this way because we'll need the map if we need to add a new World)
		@SuppressWarnings("deprecation") // forgeGetWorldMap is deprecated because it's a forge-internal-use-only method
		final Map<RegistryKey<World>, ServerWorld> map = server.forgeGetWorldMap();
		final ServerWorld existingWorld = map.get(worldKey);
		if (existingWorld != null)
		{
			return existingWorld;
		}

		return createAndRegisterWorldAndDimension(server, map, worldKey, dimensionFactory);
	}

	/**
	 * Marks a World and its Dimension for unregistration. Unregistered Worlds will stop ticking,
	 * unregistered Dimensions will not be loaded on server startup unless and until they are reregistered again.
	 * <p>
	 * Unregistration is delayed until the end of the server tick (just after the post-server-tick-event fires).
	 * <p>
	 * Players who are still in the given World at that time will be ejected to their respawn points.
	 * Players who have respawn points in Worlds being unloaded will have their spawn points reset to the overworld and respawned there.
	 * <p>
	 * Unregistering a World does not delete the region files or other data associated with the World's World folder.
	 * If a World is reregistered after unregistering it, the World will retain all prior data (unless manually deleted via server admin)
	 *
	 * @param WorldToRemove
	 *            The key for the World to schedule for unregistration. Vanilla dimensions are not removable as they are
	 *            generally assumed to exist (especially the overworld)
	 * @apiNote Not intended for use with vanilla or json dimensions, doing so may cause strange problems.
	 *          <p>
	 *          However, if a vanilla or json dimension *is* removed, restarting the server will reconstitute it as
	 *          vanilla automatically detects and registers these.
	 *          <p>
	 *          Mods whose dynamic dimensions require the ejection of players to somewhere other than their respawn point
	 *          should teleport these worlds' players to appropriate locations before unregistering their dimensions.
	 */
	public static void markDimensionForUnregistration(final MinecraftServer server, final RegistryKey<World> WorldToRemove)
	{
		if (!VANILLA_WORLDS.contains(WorldToRemove))
		{
			DynamicDimensionManager.pendingWorldsToUnregister.add(WorldToRemove);
		}
	}

	/**
	 * @return an immutable view of the Worlds pending to be unregistered and unloaded at the end of the current server tick
	 */
	public static Set<RegistryKey<World>> getWorldsPendingUnregistration()
	{
		return Collections.unmodifiableSet(DynamicDimensionManager.pendingWorldsToUnregister);
	}

	/**
	 * called at the end of the server tick just before the post-server-tick-event
	 *
	 * @deprecated Internal forge method
	 */
	@Deprecated
	public static void unregisterScheduledDimensions(final MinecraftServer server)
	{
		// flush the buffer
		final Set<RegistryKey<World>> keysToRemove = DynamicDimensionManager.pendingWorldsToUnregister;
		DynamicDimensionManager.pendingWorldsToUnregister = new HashSet<>();

		// we need to remove the dimension/world form three places
		// the server's dimension registry, the server's world registry, and the overworld's world border listener
		// the world registry is just a simple map and the world border listener has a remove() method
		// the dimension registry has five sub-collections that need to be cleaned up
		// we should also eject players from the removed worlds or they could get stuck there

		final DimensionGeneratorSettings worldGenSettings = server.getWorldData().worldGenSettings();
		final Set<RegistryKey<World>> removedWorldKeys = new HashSet<>();
		final ServerWorld overworld = server.getLevel(World.OVERWORLD);

		for (final RegistryKey<World> WorldKeyToRemove : keysToRemove)
		{
			ServerWorld removedWorld = server.forgeGetWorldMap().remove(WorldKeyToRemove); // null if the specified key was not present
			if (removedWorld != null)
			{
				// if we removed the key from the map
				// eject players from dead world
				// iterate over a copy as the world will remove players from the original list
				for (final ServerPlayerEntity player : Lists.newArrayList(removedWorld.players()))
				{
					// send players to their respawn point
					RegistryKey<World> respawnKey = player.getRespawnDimension();
					// if we're removing their respawn world then just send them to the overworld
					if (keysToRemove.contains(respawnKey))
					{
						respawnKey = World.OVERWORLD;
						player.setRespawnPosition(World.OVERWORLD, null, 0, false, false);
					}
					if (respawnKey == null)
					{
						respawnKey = World.OVERWORLD;
					}
					final ServerWorld destinationWorld = server.getLevel(respawnKey);
					BlockPos destinationPos = player.getRespawnPosition();
					if (destinationPos == null)
					{
						destinationPos = destinationWorld.getSharedSpawnPos();
					}
					final float respawnAngle = player.getRespawnAngle();
					// "respawning" the player via the player list schedules a task in the server to run after the post-server tick
					// that causes some minor logspam due to the player's world no longer being loaded
					// teleporting the player this way instead avoids this
					player.teleportTo(destinationWorld, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), respawnAngle, 0F);
				}
				// save the world now or it won't be saved later and data that may be wanted to be kept may be lost
				removedWorld.save(null, false, removedWorld.noSave());

				// fire world unload event -- when the server stops, this would fire after worlds get saved, so we'll do that here too
				MinecraftForge.EVENT_BUS.post(new WorldEvent.Unload(removedWorld));

				// remove the world border listener if possible
				final WorldBorder overworldBorder = overworld.getWorldBorder();
				final WorldBorder removedWorldBorder = removedWorld.getWorldBorder();
				final List<IBorderListener> listeners = overworldBorder.listeners;
				IBorderListener targetListener = null;
				for (IBorderListener listener : listeners)
				{
					if (listener instanceof IBorderListener.Impl && removedWorldBorder == ((IBorderListener.Impl) listener).worldBorder)
					{
						targetListener = listener;
						break;
					}
				}
				if (targetListener != null)
				{
					overworldBorder.removeListener(targetListener);
				}

				// track the removed world
				removedWorldKeys.add(WorldKeyToRemove);
			}
		}

		if (!removedWorldKeys.isEmpty())
		{
			// replace the old dimension registry with a new one containing the dimensions that weren't removed, in the same order
			final Registry<Dimension> oldRegistry = worldGenSettings.dimensions();
			final SimpleRegistry<Dimension> newRegistry = new SimpleRegistry<>(Registry.LEVEL_STEM_REGISTRY, oldRegistry.elementsLifecycle());

			for (Entry<RegistryKey<Dimension>, Dimension> entry : oldRegistry.entrySet())
			{
				final RegistryKey<Dimension> oldKey = entry.getKey();
				final RegistryKey<World> oldWorldKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, oldKey.location());
				final Dimension dimension = entry.getValue();
				if (oldKey != null && dimension != null && !removedWorldKeys.contains(oldWorldKey))
				{
					// newRegistry.register(oldKey, dimension, oldRegistry.lifecycle(dimension));
					Registry.register(newRegistry, oldKey.getRegistryName(), dimension); // @todo 1.18.2 is this right?
				}
			}

			// then replace the old registry with the new registry
			worldGenSettings.dimensions = newRegistry;

			// update the server's Worlds so dead Worlds don't get ticked
			server.markWorldsDirty();
			// client will need to be notified of the removed World for the dimension command suggester
			PacketHandler.sendToAll(new SSyncDynDimensionsPacket(ImmutableSet.of(), removedWorldKeys));
		}
	}

	@SuppressWarnings("deprecation") // because we call the forge internal method server#markWorldsDirty
	private static ServerWorld createAndRegisterWorldAndDimension(final MinecraftServer server, final Map<RegistryKey<World>, ServerWorld> map, final RegistryKey<World> worldKey, final BiFunction<MinecraftServer, RegistryKey<Dimension>, Dimension> dimensionFactory)
	{
		// get everything we need to create the dimension and the World
		final ServerWorld overworld = server.getLevel(World.OVERWORLD);

		// dimension keys have a 1:1 relationship with World keys, they have the same IDs as well
		final RegistryKey<Dimension> dimensionKey = RegistryKey.create(Registry.LEVEL_STEM_REGISTRY, worldKey.location());
		final Dimension dimension = dimensionFactory.apply(server, dimensionKey);

		// the int in create() here is radius of chunks to watch, 11 is what the server uses when it initializes worlds
		final IChunkStatusListener chunkProgressListener = server.progressListenerFactory.create(11);
		final Executor executor = server.executor;
		final SaveFormat.LevelSave anvilConverter = server.storageSource;
		final IServerConfiguration worldData = server.getWorldData();
		final DimensionGeneratorSettings worldGenSettings = worldData.worldGenSettings();
		final DerivedWorldInfo derivedWorldData = new DerivedWorldInfo(worldData, worldData.overworldData());
		
		// now we have everything we need to create the dimension and the World
		// this is the same order server init creates Worlds:
		// the dimensions are already registered when Worlds are created, we'll do that first
		// then instantiate World, add border listener, add to map, fire world load event

		// register the actual dimension
		// Registry.register(worldGenSettings.dimensions(), dimensionKey, dimension);
		Registry<Dimension> dimensionRegistry = worldGenSettings.dimensions();
		if (dimensionRegistry instanceof MutableRegistry)
		{
			((MutableRegistry<Dimension>)dimensionRegistry).register(dimensionKey, dimension, Lifecycle.stable());
		}
		else
		{
			throw new IllegalStateException("Unable to register dimension '" + dimensionKey.location() + "'! Registry not writable!");
		}

		// create the world instance
		final ServerWorld newWorld = new ServerWorld(
			server, 
			executor, 
			anvilConverter, 
			derivedWorldData, 
			worldKey, 
			dimension.typeSupplier().get(), 
			chunkProgressListener, 
			dimension.generator(), 
			worldGenSettings.isDebug(), 
			BiomeManager.obfuscateSeed(worldGenSettings.seed()), 
			ImmutableList.of(), // "special spawn list"
			// phantoms, travelling traders, patrolling/sieging raiders, and cats are overworld special spawns
			// this is always empty for non-overworld dimensions (including json dimensions)
			// these spawners are ticked when the world ticks to do their spawning logic,
			// mods that need "special spawns" for their own dimensions should implement them via tick events or other systems
			false // "tick time", true for overworld, always false for nether, end, and json dimensions
		);

		// add world border listener, for parity with json dimensions
		// the vanilla behaviour is that world borders exist in every dimension simultaneously with the same size and position
		// these border listeners are automatically added to the overworld as worlds are loaded, so we should do that here too
		// TODO if world-specific world borders are ever added, change it here too
		overworld.getWorldBorder().addListener(new IBorderListener.Impl(newWorld.getWorldBorder()));

		// register World
		map.put(worldKey, newWorld);

		// update forge's world cache so the new World can be ticked
		server.markWorldsDirty();

		// fire world load event
		MinecraftForge.EVENT_BUS.post(new WorldEvent.Load(newWorld));

		// update clients' dimension lists
		PacketHandler.sendToAll(new SSyncDynDimensionsPacket(ImmutableSet.of(worldKey), ImmutableSet.of()));

		return newWorld;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ChallengeDimensionRenderInfo extends DimensionRenderInfo {
		public ChallengeDimensionRenderInfo() {
			super(Float.NaN, false, FogType.NORMAL, false, false);
		}

		@Override
		public Vector3d getBrightnessDependentFogColor(Vector3d p_230494_1_, float p_230494_2_) {
			return p_230494_1_.multiply(p_230494_2_ * 0.94F + 0.06F, p_230494_2_ * 0.94F + 0.06F, p_230494_2_ * 0.91F + 0.09F);
		}

		@Override
		public boolean isFoggyAt(int p_230493_1_, int p_230493_2_) {
			return false;
		}
	}
}