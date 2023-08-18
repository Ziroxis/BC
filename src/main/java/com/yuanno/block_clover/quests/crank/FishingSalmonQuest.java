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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class FishingSalmonQuest extends Quest {
    
    public static final QuestId INSTANCE = new QuestId.Builder("Hungy for salmon", FishingSalmonQuest::new)
            .build();
    private Objective objective = new ObtainItemObjective("Obtain 8 salmon", 8, Items.SALMON.delegate);

    public FishingSalmonQuest(QuestId questId)
    {
        super(questId);
        this.addObjective(objective);
        this.setDescription("Fish some salmon");
        this.setRank(ModValues.RANK_QUEST_C);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.SALMON, 8))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(200);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
