package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.PlayerOverviewScreen;
import com.yuanno.block_clover.client.gui.PlayerStatsScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenPlayerStatsScreenPacket {

    public SOpenPlayerStatsScreenPacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static SOpenPlayerStatsScreenPacket decode(PacketBuffer buffer)
    {
        SOpenPlayerStatsScreenPacket msg = new SOpenPlayerStatsScreenPacket();
        return msg;
    }

    public static void handle(SOpenPlayerStatsScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> SOpenPlayerStatsScreenPacket.ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenPlayerStatsScreenPacket message)
        {
            PlayerStatsScreen.open();
        }
    }}
