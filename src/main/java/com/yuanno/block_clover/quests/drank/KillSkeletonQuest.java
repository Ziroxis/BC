package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillSkeletonQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Rattle 'em", KillSkeletonQuest::new)
            .build();
    private Objective killEntityObjective = new KillEntityObjective("Kill 15 skeletons", 15, EntityType.SKELETON);

    public KillSkeletonQuest(QuestId questId)
    {
        super(questId);
        this.addObjectives(killEntityObjective);
        this.setDescription("Kill a few skeletons");

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
