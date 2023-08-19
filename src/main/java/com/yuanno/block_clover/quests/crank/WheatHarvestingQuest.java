package com.yuanno.block_clover.quests.crank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class WheatHarvestingQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Starting a wheat farm", WheatHarvestingQuest::new)
            .build();
    private Objective obtainItemObjective = new ObtainItemObjective("Obtain 32 wheat", 32, Items.WHEAT.delegate);

    public WheatHarvestingQuest(QuestId id)
    {
        super(id);
        this.addObjective(this.obtainItemObjective);
        this.setDescription("Obtain wheat");
        this.setRank(ModValues.RANK_QUEST_C);
        this.onCompleteEvent = this::giveReward;


    }
    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.WHEAT, 32))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(200);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
