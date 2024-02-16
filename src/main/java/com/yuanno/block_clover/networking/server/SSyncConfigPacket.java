package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.data.config.ConfigCapability;
import com.yuanno.block_clover.data.config.IConfig;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
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

public class SSyncConfigPacket {

    private int entityId;
    private INBT data;

    public SSyncConfigPacket()
    {
    }

    public SSyncConfigPacket(int entityId, IConfig props)
    {
        this.data = new CompoundNBT();
        this.data = ConfigCapability.INSTANCE.getStorage().writeNBT(ConfigCapability.INSTANCE, props, null);
        this.entityId = entityId;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.entityId);
        buffer.writeNbt((CompoundNBT) this.data);
    }

    public static SSyncConfigPacket decode(PacketBuffer buffer)
    {
        SSyncConfigPacket msg = new SSyncConfigPacket();
        msg.entityId = buffer.readInt();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(SSyncConfigPacket message, final Supplier<NetworkEvent.Context> ctx)
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
        public static void handle(SSyncConfigPacket message)
        {
            Entity target = Minecraft.getInstance().level.getEntity(message.entityId);
            if (target == null || !(target instanceof LivingEntity))
                return;

            IConfig props = ConfigCapability.get((LivingEntity) target);
            ConfigCapability.INSTANCE.getStorage().readNBT(ConfigCapability.INSTANCE, props, null, message.data);
        }
    }
}
