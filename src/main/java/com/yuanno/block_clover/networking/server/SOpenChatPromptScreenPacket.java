package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.npc.ChatPromptScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenChatPromptScreenPacket {

    private int questerEntity;
    public SOpenChatPromptScreenPacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static SOpenChatPromptScreenPacket decode(PacketBuffer buffer)
    {
        SOpenChatPromptScreenPacket msg = new SOpenChatPromptScreenPacket();
        return msg;
    }

    public static void handle(SOpenChatPromptScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenChatPromptScreenPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;


            Minecraft.getInstance().setScreen(new ChatPromptScreen(player));
        }
    }
}
