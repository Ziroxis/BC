package com.yuanno.block_clover.spells.misc;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;

public class ManaSenseAbility extends ContinuousAbility implements IParallelContinuousAbility {


    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mana sense", AbilityCategories.AbilityCategory.MISCELLANEOUS, ManaSenseAbility.class)
            .setDescription("Senses mana from other entities around you")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();


    public ManaSenseAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(5);
        this.setExperiencePoint(5);
        this.setExperienceGainLevelCap(30);
        this.setEvolutionCost(50);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        return true;
    }


    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        return true;
    }
}
