package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncDevilStatsPacket {

    private INBT data;

    public CSyncDevilStatsPacket() {}

    public CSyncDevilStatsPacket(IDevil props)
    {
        this.data = new CompoundNBT();
        this.data = DevilCapability.INSTANCE.getStorage().writeNBT(DevilCapability.INSTANCE, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSyncDevilStatsPacket decode(PacketBuffer buffer)
    {
        CSyncDevilStatsPacket msg = new CSyncDevilStatsPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSyncDevilStatsPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IDevil props = DevilCapability.get(player);

                DevilCapability.INSTANCE.getStorage().readNBT(DevilCapability.INSTANCE, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}
