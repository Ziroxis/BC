package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenpacket;
import com.yuanno.block_clover.networking.server.SOpenPlayerStatsScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenDevilSummoningScreenPacket {

    public COpenDevilSummoningScreenPacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static COpenDevilSummoningScreenPacket decode(PacketBuffer buffer)
    {
        COpenDevilSummoningScreenPacket msg = new COpenDevilSummoningScreenPacket();

        return msg;
    }

    public static void handle(COpenDevilSummoningScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();

                PacketHandler.sendTo(new SOpenDevilSummoningScreenpacket(), player);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
