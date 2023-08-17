package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillEndermanQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("They teleport too much", KillEndermanQuest::new)
            .build();
    private Objective killEntityObjective = new KillEntityObjective("Kill 5 enderman", 5, EntityType.ENDERMAN);

    public KillEndermanQuest(QuestId questId)
    {
        super(questId);
        this.addObjectives(killEntityObjective);
        this.setDescription("Kill a few enderman");

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        System.out.println("QUEST DONE");
        return true;
    }
}
