package com.yuanno.block_clover.quests.brank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainGoldQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Ready for gold", ObtainGoldQuest::new)
            .build();
    private Objective objective01 = new ObtainItemObjective("Obtain 8 gold", 8, Items.GOLD_INGOT.delegate);

    public ObtainGoldQuest(QuestId id)
    {
        super(id);
        this.addObjectives(this.objective01);
        this.setDescription("Obtain a few gold ingots");
        this.setRank(ModValues.RANK_QUEST_B);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.GOLD_INGOT, 8))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(400);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
