package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainCoalQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Santa came by...", ObtainCoalQuest::new)
            .build();
    private Objective objective01 = new ObtainItemObjective("Obtain 32 coal", 32, Items.COAL.delegate);

    public ObtainCoalQuest(QuestId id)
    {
        super(id);
        this.addObjectives(this.objective01);
        this.setDescription("Obtain a bit of coal");

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("GOTTEM");
        return true;
    }
}
