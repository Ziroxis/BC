package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainCoalQuest extends Quest {

    private Objective collectCoalObjective = new ObtainItemObjective("Get some coal", 32, Items.COAL_ORE.delegate);

    public ObtainCoalQuest()
    {
        super("obtaincoalquest", "Obtain some coal");
        this.setDescription("Collect some coal");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.collectCoalObjective);

        this.onCompleteEvent = this::giveReward;

    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
