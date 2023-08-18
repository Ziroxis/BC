package com.yuanno.block_clover.quests.brank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainGoldQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Ready for gold", ObtainGoldQuest::new)
            .build();
    private Objective objective01 = new ObtainItemObjective("Obtain 8 gold", 8, Items.IRON_INGOT.delegate);

    public ObtainGoldQuest(QuestId id)
    {
        super(id);
        this.addObjectives(this.objective01);
        this.setDescription("Obtain a few gold ingots");
        this.setRank(ModValues.RANK_QUEST_B);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
