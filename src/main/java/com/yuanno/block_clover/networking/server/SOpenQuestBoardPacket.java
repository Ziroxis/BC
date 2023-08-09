package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.block.QuestBoardScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenQuestBoardPacket {

    public SOpenQuestBoardPacket() {}


    public void encode(PacketBuffer buffer)
    {

    }

    public static SOpenQuestBoardPacket decode(PacketBuffer buffer)
    {
        SOpenQuestBoardPacket msg = new SOpenQuestBoardPacket();
        return msg;
    }

    public static void handle(SOpenQuestBoardPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenQuestBoardPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;


            Minecraft.getInstance().setScreen(new QuestBoardScreen(player));
        }
    }
}
