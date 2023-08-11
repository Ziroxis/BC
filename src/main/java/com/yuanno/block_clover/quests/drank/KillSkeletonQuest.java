package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillSkeletonQuest extends Quest {

    private Objective killSkeletonQuest = new KillEntityObjective("Kill 15 skeletons", 15, EntityType.SKELETON);

    public KillSkeletonQuest()
    {
        super("skeletonquest", "Kill 15 skeletons");
        this.setDescription("Kill 15 skeletons");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.killSkeletonQuest);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
