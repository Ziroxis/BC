package com.yuanno.block_clover.networking.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SummonDevilEntityPacket {
    private ResourceLocation entityId; // Some information about the entity you want to spawn

    public SummonDevilEntityPacket(ResourceLocation entityId) {
        this.entityId = entityId;
    }

    public static void encode(SummonDevilEntityPacket message, PacketBuffer buffer) {
        buffer.writeResourceLocation(message.entityId);
    }

    public static SummonDevilEntityPacket decode(PacketBuffer buffer) {
        ResourceLocation entityId = buffer.readResourceLocation();
        return new SummonDevilEntityPacket(entityId);
    }

    public static void handle(SummonDevilEntityPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
                EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(message.entityId);
                Entity entity = entityType.create(player.level);
                entity.setPos(player.getX() - 10, player.getY(), player.getZ());
                player.level.addFreshEntity(entity);

            });
        }
        ctx.get().setPacketHandled(true);
    }
}
