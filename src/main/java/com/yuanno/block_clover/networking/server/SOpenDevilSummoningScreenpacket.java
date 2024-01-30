package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.DevilSummoningScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenDevilSummoningScreenpacket {

    public SOpenDevilSummoningScreenpacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static SOpenDevilSummoningScreenpacket decode(PacketBuffer buffer)
    {
        SOpenDevilSummoningScreenpacket msg = new SOpenDevilSummoningScreenpacket();
        return msg;
    }

    public static void handle(SOpenDevilSummoningScreenpacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> SOpenDevilSummoningScreenpacket.ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenDevilSummoningScreenpacket message)
        {
            DevilSummoningScreen.open();
        }
    }
}
