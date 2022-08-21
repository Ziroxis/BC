package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class SealingPunchAbility extends PunchAbility {
    public static final Ability INSTANCE = new SealingPunchAbility();

    public SealingPunchAbility()
    {
        super("Sealing punch", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Punches an entity, sealing him in place for a few seconds.\nIt also empowers the punch.");
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setExperiencePoint(30);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        if (!target.hasEffect(ModEffects.SEALING.get()))
            target.addEffect(new EffectInstance(ModEffects.SEALING.get(), 100, 0));

        return 3;
    }
}
