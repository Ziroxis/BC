package com.yuanno.block_clover.quests.brank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainGoldQuest extends Quest {

    private Objective collectGoldObejctive = new ObtainItemObjective("Get some gold", 8, Items.GOLD_INGOT.delegate);

    public ObtainGoldQuest()
    {
        super("obtaingoldquest", "Obtain some gold");
        this.setDescription("Collect some gold");
        this.setRank(ModValues.RANK_QUEST_B);
        this.addObjective(this.collectGoldObejctive);

        this.onCompleteEvent = this::giveReward;

    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
