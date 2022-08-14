package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenPlayerScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenPlayerStatsScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenPlayerStatsScreenPacket {

    public COpenPlayerStatsScreenPacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static COpenPlayerStatsScreenPacket decode(PacketBuffer buffer)
    {
        COpenPlayerStatsScreenPacket msg = new COpenPlayerStatsScreenPacket();

        return msg;
    }

    public static void handle(COpenPlayerStatsScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IEntityStats entityProps = EntityStatsCapability.get(player);

                PacketHandler.sendTo(new SOpenPlayerStatsScreenPacket(), player);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
