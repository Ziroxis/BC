package com.yuanno.block_clover.data.world;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.api.challenges.InProgressChallenge;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.init.ModResources;
import com.yuanno.block_clover.world.ChallengesChunkGenerator;
import com.yuanno.block_clover.world.DynamicDimensionManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class ChallengesWorldData extends WorldSavedData {
	private static final String IDENTIFIER = "soulsawakening-challenges";
	private static final TranslationTextComponent NOT_UNLOCKED = new TranslationTextComponent("Not Unlocked");

	private Map<UUID, InProgressChallenge> inProgressChallenges = new HashMap<>();

	public ChallengesWorldData() {
		super(IDENTIFIER);
	}

	/** Will return null when used from the client side */
	@Nullable
	public static ChallengesWorldData get() {
		if (ServerLifecycleHooks.getCurrentServer() != null) {
			return ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage().computeIfAbsent(ChallengesWorldData::new, IDENTIFIER);
		}
		return null;
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		return nbt;
	}

	@Override
	public void load(CompoundNBT nbt) {

	}

	public boolean startChallenge(ServerPlayerEntity player, List<LivingEntity> group, ChallengeCore core, boolean isFree) {
		IChallengesData props = ChallengesDataCapability.get(player);
		if (props != null) {
			Challenge challenge = props.getChallenge(core);
			if (challenge == null) {
				player.sendMessage(NOT_UNLOCKED, Util.NIL_UUID);
				return false;
			}
			ResourceLocation dimName = new ResourceLocation(Main.MODID, "challenges_" + player.getStringUUID());
			RegistryKey<World> dimension = RegistryKey.create(Registry.DIMENSION_REGISTRY, dimName);
			DynamicRegistries registryAccess = player.level.registryAccess();
			Supplier<DimensionType> type = () -> registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).getOrThrow(RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModResources.DIMENSION_TYPE_CHALLENGES));

			ServerWorld shard = DynamicDimensionManager.getOrCreateWorld(player.getServer(), dimension, ((minecraftServer, levelStemResourceKey) -> {
				ChunkGenerator generator = new ChallengesChunkGenerator(registryAccess.registryOrThrow(Registry.BIOME_REGISTRY));
				return new Dimension(type, generator);
			}));

			UUID id = player.getUUID();
			InProgressChallenge inProgressChallenge = new InProgressChallenge(id, player, shard, group, challenge, isFree);
			if (this.inProgressChallenges.containsKey(id)) {
				this.stopChallenge(inProgressChallenge);
			}
			this.inProgressChallenges.put(id, inProgressChallenge);
			this.setDirty();
			return true;
		}

		return false;
	}

	public void stopChallenge(InProgressChallenge inProgChallenge) {
		for (LivingEntity entity : inProgChallenge.getGroup()) {
			if (entity.isAlive() && Beapi.isInChallengeDimension(entity.level) && entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				ServerWorld overworld = player.getServer().overworld();
				BlockPos spawnPos = overworld.getSharedSpawnPos();
				player.teleportTo(overworld, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.yRot, player.xRot);
			}
		}
//		inProgChallenge.cleanupArena();
		this.inProgressChallenges.remove(inProgChallenge.getId());
		this.setDirty();
	}
	
	@Nullable
	public InProgressChallenge getInProgressChallengeFor(LivingEntity entity) {
		UUID id = entity.getUUID();
		return this.inProgressChallenges.get(id);
	}
	
	public void tick(ServerWorld world) {
		for (InProgressChallenge inProgressChallenge : this.inProgressChallenges.values()) {
			if (inProgressChallenge.canDelete()) {
				this.stopChallenge(inProgressChallenge);
			}
		}
		
		for (InProgressChallenge inProgressChallenge : this.inProgressChallenges.values()) {
			inProgressChallenge.tick();
		}
	}
}
