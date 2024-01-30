package com.yuanno.block_clover.networking.server;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class SSyncDynDimensionsPacket
{
	private Set<RegistryKey<World>> addedDims;
	private Set<RegistryKey<World>> removedDims;

	public SSyncDynDimensionsPacket()
	{
	}

	public SSyncDynDimensionsPacket(Set<RegistryKey<World>> addedDims, Set<RegistryKey<World>> removedDims)
	{
		this.addedDims = addedDims;
		this.removedDims = removedDims;
	}

	public void encode(PacketBuffer buf)
	{
		buf.writeVarInt(this.addedDims.size());
		this.addedDims.forEach(key -> buf.writeResourceLocation(key.location()));
		buf.writeVarInt(this.removedDims.size());
		this.removedDims.forEach(key -> buf.writeResourceLocation(key.location()));
	}

	public static SSyncDynDimensionsPacket decode(PacketBuffer buf)
	{
		SSyncDynDimensionsPacket msg = new SSyncDynDimensionsPacket();

		Set<RegistryKey<World>> addedDims = new HashSet<>();
		Set<RegistryKey<World>> removedDims = new HashSet<>();

		final int addedSize = buf.readVarInt();
		for (int i = 0; i < addedSize; i++)
		{
			final ResourceLocation dim = buf.readResourceLocation();
			addedDims.add(RegistryKey.create(Registry.DIMENSION_REGISTRY, dim));
		}
		
        final int removedSize = buf.readVarInt();
        for (int i = 0; i < removedSize; i++) {
            final ResourceLocation dim = buf.readResourceLocation();
            removedDims.add(RegistryKey.create(Registry.DIMENSION_REGISTRY, dim));
        }
        
        msg.addedDims = addedDims;
        msg.removedDims = removedDims;
        
		return msg;
	}

	public static void handle(SSyncDynDimensionsPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SSyncDynDimensionsPacket msg)
		{
			ClientPlayerEntity player = Minecraft.getInstance().player;
			if (player == null) {
				return;				
			}

			Set<RegistryKey<World>> levels = player.connection.levels();
			levels.addAll(msg.addedDims);
			msg.removedDims.forEach(levels::remove);
		}
	}
}
