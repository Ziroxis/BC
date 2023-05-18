package com.yuanno.block_clover.spells.curse;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class LifeCurseAbility extends PunchAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Life Curse", AbilityCategories.AbilityCategory.DEVIL, LifeCurseAbility.class)
            .setDescription("Touch another player or entity, cursing them to die earlier")
            .setDamageKind(AbilityDamageKind.CURSE)
            .build();

    public LifeCurseAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(360);
        this.setmanaCost(0);
        this.setExperiencePoint(0);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        target.addEffect(new EffectInstance(ModEffects.LIFE_CURSE.get(), 120, 0));
        return 0;
    }
}
