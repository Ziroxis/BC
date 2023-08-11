package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillSpiderQuest extends Quest {

    private Objective killSpiderObjective = new KillEntityObjective("Kill 5 spiders", 5, EntityType.SPIDER);

    public KillSpiderQuest()
    {
        super("spiderquest", "Kill 5 spiders");
        this.setDescription("Kill 5 spiders");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.killSpiderObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
