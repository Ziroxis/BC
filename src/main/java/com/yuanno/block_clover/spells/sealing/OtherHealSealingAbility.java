package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class OtherHealSealingAbility extends PunchAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Other heal sealing", AbilityCategories.AbilityCategory.ATTRIBUTE, OtherHealSealingAbility.class)
            .setDescription("Touch an entity, healing it.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public OtherHealSealingAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(15);
        this.setmanaCost(25);
        this.setExperiencePoint(35);
        this.onHitEntityEvent = this::onHitEntity;
    }

    private float onHitEntity(PlayerEntity player, LivingEntity livingEntity)
    {
        livingEntity.heal(8);
        return 1;
    }
}
