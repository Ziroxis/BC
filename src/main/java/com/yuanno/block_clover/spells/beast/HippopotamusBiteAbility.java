package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class HippopotamusBiteAbility extends PunchAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Hippopotamus bite", AbilityCategories.AbilityCategory.ATTRIBUTE, HippopotamusBiteAbility.class)
            .setDescription("Bite your enemy with the full force of a hippopotamus")
            .setDamageKind(AbilityDamageKind.SLASH)
            .build();

    public HippopotamusBiteAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(30);
        this.setExperiencePoint(40);
        this.setExperienceGainLevelCap(50);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        int damage;
        damage = 22;
        double knockback = 1.5;
        ((LivingEntity)target).knockback((float)knockback * 0.5F, (double) MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180F))));
        return damage;
    }
}
