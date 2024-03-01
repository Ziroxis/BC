package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

public class EarthGlovesAbility extends PunchAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Earth gloves", AbilityCategories.AbilityCategory.ATTRIBUTE, EarthGlovesAbility.class)
            .setDescription("Makes gloves out of earth")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();

    private static final AttributeModifier EARTH_GLOVES = new AttributeModifier(UUID.fromString("60fbf220-0222-11ed-b939-0242ac120002"),
            "Earth Gloves", 5, AttributeModifier.Operation.ADDITION);

    public EarthGlovesAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(5);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(20);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(EARTH_GLOVES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(EARTH_GLOVES);
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
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(EARTH_GLOVES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(EARTH_GLOVES);
        return true;
    }


}
