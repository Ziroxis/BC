package com.yuanno.block_clover.quests.crank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillCreeperQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("They keep exploding", KillCreeperQuest::new)
            .build();
    private Objective killCreeperObjective = new KillEntityObjective("Kill 10 creepers", 10, EntityType.CREEPER);

    public KillCreeperQuest(QuestId questId)
    {
        super(questId);
        this.addObjectives(killCreeperObjective);
        this.setDescription("Kill a few creepers");
        this.setRank(ModValues.RANK_QUEST_C);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(200);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
