package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.block.DevilSummoningScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenDevilSummoningScreenPacket {

    public SOpenDevilSummoningScreenPacket() {}


    public void encode(PacketBuffer buffer)
    {

    }

    public static SOpenDevilSummoningScreenPacket decode(PacketBuffer buffer)
    {
        SOpenDevilSummoningScreenPacket msg = new SOpenDevilSummoningScreenPacket();
        return msg;
    }

    public static void handle(SOpenDevilSummoningScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenDevilSummoningScreenPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;


            Minecraft.getInstance().setScreen(new DevilSummoningScreen());
        }
    }
}
