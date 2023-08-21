package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.misc.ManaSenseAbility;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaSenseQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Mana sense", ManaSenseQuest::new)
            .build();

    private Objective useAbility = new UseContinuousAbilityObjective("Use mana skin for 2 minutes while standing still", 1200, ManaSkinAbility.INSTANCE, true);

    public ManaSenseQuest(QuestId id)
    {
        super(id);
        this.addObjective(this.useAbility);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(player, ManaSenseAbility.INSTANCE);

        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;

    }

}
