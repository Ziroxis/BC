package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainIronQuest extends Quest {

    private Objective collectIronObjective = new ObtainItemObjective("Get some iron", 16, Items.IRON_INGOT.delegate);

    public ObtainIronQuest()
    {
        super("obtainironquest", "Obtain some iron");
        this.setDescription("Collect some iron");
        this.setRank(ModValues.RANK_QUEST_D);
        this.addObjective(this.collectIronObjective);

        this.onCompleteEvent = this::giveReward;

    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
