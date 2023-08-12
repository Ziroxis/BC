package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.client.IDynamicRenderer;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenChatPromptScreenPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;

public class NPCentity extends BCentity implements IDynamicRenderer {

    public boolean preRequisite = false;
    public int requisite = 0; // 0 = nothing; 1 = level; 2 = quest
    //public int levelRequisite = 0;
    public ArrayList<Integer> levelrequisites = new ArrayList<Integer>();
    //public Quest quest;
    public Quest questRequisite;
    public ArrayList<Quest> quests = new ArrayList<Quest>();
    public String requisiteSpeech;
    public String questChoiceSpeech;
    public String acceptanceSpeech;
    public String declineSpeech;
    public String waitingSpeech;
    public String doneSpeech;

    protected NPCentity(EntityType type, World world) {
        super(type, world);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        if (hand != Hand.MAIN_HAND)
            return ActionResultType.PASS;
        if (!player.level.isClientSide) {
            this.lookAt(player, 1, 1);
            PacketHandler.sendTo(new SOpenChatPromptScreenPacket(this.getId()), player);
        }
        return ActionResultType.PASS;
    }

    @Override
    public String getMobTexture() {
        return "grimoire_magician1";
    }

    @Override
    public String getDefaultTexture() {
        return null;
    }
}
