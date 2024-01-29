package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncDevilPacket {

    private int entityId;
    private INBT data;

    public SSyncDevilPacket()
    {
    }

    public SSyncDevilPacket(int entityId, IDevil props)
    {
        this.data = new CompoundNBT();
        this.data = DevilCapability.INSTANCE.getStorage().writeNBT(DevilCapability.INSTANCE, props, null);
        this.entityId = entityId;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.entityId);
        buffer.writeNbt((CompoundNBT) this.data);
    }

    public static SSyncDevilPacket decode(PacketBuffer buffer)
    {
        SSyncDevilPacket msg = new SSyncDevilPacket();
        msg.entityId = buffer.readInt();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(SSyncDevilPacket message, final Supplier<NetworkEvent.Context> ctx)
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
        public static void handle(SSyncDevilPacket message)
        {
            Entity target = Minecraft.getInstance().level.getEntity(message.entityId);
            if (target == null || !(target instanceof LivingEntity))
                return;

            IDevil props = DevilCapability.get((LivingEntity) target);
            DevilCapability.INSTANCE.getStorage().readNBT(DevilCapability.INSTANCE, props, null, message.data);

        }
    }
}
