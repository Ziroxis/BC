package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.npc.ChatPromptQuestBoardScreen;
import com.yuanno.block_clover.entities.api.BCentity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenChatPromptScreenQuestBoardPacket {

    private int questerEntity;
    public SOpenChatPromptScreenQuestBoardPacket() {}
    public SOpenChatPromptScreenQuestBoardPacket(int questerEntity)
    {
        this.questerEntity = questerEntity;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.questerEntity);
    }

    public static SOpenChatPromptScreenQuestBoardPacket decode(PacketBuffer buffer)
    {
        SOpenChatPromptScreenQuestBoardPacket msg = new SOpenChatPromptScreenQuestBoardPacket();
        msg.questerEntity = buffer.readInt();
        return msg;
    }

    public static void handle(SOpenChatPromptScreenQuestBoardPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenChatPromptScreenQuestBoardPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;
            Entity questGiver = Minecraft.getInstance().level.getEntity(message.questerEntity);
            if (!(questGiver instanceof BCentity))
                return;

            Minecraft.getInstance().setScreen(new ChatPromptQuestBoardScreen(player, (BCentity) questGiver));
        }
    }
}
