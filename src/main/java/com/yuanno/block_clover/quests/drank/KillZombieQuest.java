package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillZombieQuest extends Quest {

    private Objective killZombieObjective = new KillEntityObjective("Kill 20 zombies", 20, EntityType.ZOMBIE);

    public KillZombieQuest()
    {
        super("zombiequest", "Kill 10 zombies");
        this.setDescription("Kill 10 zombies");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.killZombieObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
