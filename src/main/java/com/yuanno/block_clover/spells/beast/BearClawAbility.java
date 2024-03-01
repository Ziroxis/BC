package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.spells.earth.EarthGlovesAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

public class BearClawAbility extends PunchAbility implements IParallelContinuousAbility {


    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Bear claw", AbilityCategories.AbilityCategory.ATTRIBUTE, BearClawAbility.class)
            .setDescription("Makes a bear claw out of mana around your fist")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();

    private static final AttributeModifier BEAR_CLAW = new AttributeModifier(UUID.fromString("9c08968c-c5b0-11ed-afa1-0242ac120002"),
            "Bear claw", 5, AttributeModifier.Operation.ADDITION);

    public BearClawAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(5);
        this.setExperiencePoint(5);
        this.setExperienceGainLevelCap(30);
        this.setEvolutionCost(100);
        this.setStoppingAfterHit(false);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
        this.onStopContinuityEvent = this::onStopContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);

        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(BEAR_CLAW);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(BEAR_CLAW);
        return true;

    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        double knockback = 1.5;
        ((LivingEntity)target).knockback((float)knockback * 0.5F, (double) MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180F))));
        return 3;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(BEAR_CLAW);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(BEAR_CLAW);
        return true;

    }

    private boolean onStopContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(BEAR_CLAW);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(BEAR_CLAW);
        return true;
    }
}
