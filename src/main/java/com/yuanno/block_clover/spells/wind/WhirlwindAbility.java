package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class WhirlwindAbility extends PunchAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Whirlwind", AbilityCategories.AbilityCategory.ATTRIBUTE, WhirlwindAbility.class)
            .setDescription("Envelops your punches with wind, hitting an enemy locks them in a wind")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public WhirlwindAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(10);
        this.setExperiencePoint(10);
        this.setStoppingAfterHit(false);

        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        System.out.println("HIT");
        target.addEffect(new EffectInstance(ModEffects.WHIRLWIND.get(), 80, 0));
        return 0;
    }
}
