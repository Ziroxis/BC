package com.example.block_clover.spells.light;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LightHealingAbility extends Ability {
    public static final LightHealingAbility INSTANCE = new LightHealingAbility();

    public LightHealingAbility()
    {
        super("Light Healing", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Heals yourself with the power of light");
        this.setmanaCost(25);
        this.setMaxCooldown(20);
        this.setExperiencePoint(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        player.addEffect(new EffectInstance(Effects.REGENERATION, 80, 3));

        return true;
    }
}
