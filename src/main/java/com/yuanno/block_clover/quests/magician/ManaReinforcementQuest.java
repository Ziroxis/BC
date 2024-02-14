package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.misc.ManaReinforcementAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaReinforcementQuest extends Quest {
    public static final QuestId INSTANCE = new QuestId.Builder("Mana reinforcement", ManaReinforcementQuest::new)
            .build();

    private Objective levelObjective = new ReachLevelObjective("Become stronger", 10);

    public ManaReinforcementQuest(QuestId questId)
    {
        super(questId);
        this.addObjective(this.levelObjective);
        this.setCategory(Category.MAGICIAN);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(player, ManaReinforcementAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
