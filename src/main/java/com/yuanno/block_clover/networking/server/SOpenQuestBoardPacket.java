package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.client.gui.screen.block.QuestBoardScreen;
import com.yuanno.block_clover.init.ModQuests;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SOpenQuestBoardPacket {
    private List<Quest> questList;
    public SOpenQuestBoardPacket() {}

    public SOpenQuestBoardPacket(List<Quest> questList)
    {
        this.questList = questList;
    }
    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.questList.size());
        for (Quest quest : this.questList)
        {
            buffer.writeUtf(quest.getTitle());
        }
    }

    public static SOpenQuestBoardPacket decode(PacketBuffer buffer)
    {
        SOpenQuestBoardPacket msg = new SOpenQuestBoardPacket();
        int size = buffer.readInt();
        List<String> entries = new ArrayList<String>();
        for (int i = 0; i < size; i++)
        {
            entries.add(buffer.readUtf());
        }
        ArrayList<Quest> questArrayList = new ArrayList<>();
        for (Quest quest : ModQuests.QUESTBOARD_QUESTS)
        {
            if (entries.contains(quest.getTitle()))
                questArrayList.add(quest);
        }
        msg.questList = questArrayList;
        return msg;
    }

    public static void handle(SOpenQuestBoardPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                ClientHandler.handle(message);
            });
        }
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenQuestBoardPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;

            Minecraft.getInstance().setScreen(new QuestBoardScreen(player, message.questList));
        }
    }
}
