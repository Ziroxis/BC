package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.init.ModValues;
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

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
