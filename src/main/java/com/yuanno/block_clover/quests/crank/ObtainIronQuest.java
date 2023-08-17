package com.yuanno.block_clover.quests.crank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainIronQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Going for iron", ObtainIronQuest::new)
            .build();
    private Objective objective01 = new ObtainItemObjective("Obtain 16 iron", 16, Items.IRON_INGOT.delegate);

    public ObtainIronQuest(QuestId id)
    {
        super(id);
        this.addObjectives(this.objective01);
        this.setDescription("Obtain a few iron ingots");
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
