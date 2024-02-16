package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.config.ConfigCapability;
import com.yuanno.block_clover.data.config.IConfig;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncConfigPacket {

    private INBT data;

    public CSyncConfigPacket() {}

    public CSyncConfigPacket(IConfig props)
    {
        this.data = new CompoundNBT();
        this.data = ConfigCapability.INSTANCE.getStorage().writeNBT(ConfigCapability.INSTANCE, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSyncConfigPacket decode(PacketBuffer buffer)
    {
        CSyncConfigPacket msg = new CSyncConfigPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSyncConfigPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IConfig props = ConfigCapability.get(player);

                ConfigCapability.INSTANCE.getStorage().readNBT(ConfigCapability.INSTANCE, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}
