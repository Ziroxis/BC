package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillCreeperQuest extends Quest {

    private Objective killCreeperObjective = new KillEntityObjective("Kill 10 creepers", 10, EntityType.CREEPER);

    public KillCreeperQuest()
    {
        super("creeperquest", "Kill 10 creepers");
        this.setDescription("Kill 10 creepers");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.killCreeperObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
