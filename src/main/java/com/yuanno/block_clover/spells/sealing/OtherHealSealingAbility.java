package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class OtherHealSealingAbility extends PunchAbility {
    public static final Ability INSTANCE = new OtherHealSealingAbility();

    public OtherHealSealingAbility()
    {
        super("Other Heal Sealing", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Touch an entity, healing it");
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
