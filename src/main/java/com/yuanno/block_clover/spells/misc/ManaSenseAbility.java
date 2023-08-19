package com.yuanno.block_clover.spells.misc;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousPunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

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
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        return true;
    }


    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        return true;
    }
}
