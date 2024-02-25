package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class WaterBlessingAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Blessing", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterBlessingAbility.class)
            .setDescription("Bless yourself, being able to swim more potently in the water")
            .build();

    public WaterBlessingAbility()
    {
        super(INSTANCE);
        this.duringPassiveEvent = this::duringContinuity;
    }

    public void duringContinuity(PlayerEntity player)
    {
        if (player.isInWater())
        {
            if (!player.hasEffect(Effects.WATER_BREATHING))
                player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 0));
        }
    }
}
