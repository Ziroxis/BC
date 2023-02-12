package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightHealingParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LightHealingAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Light healing", AbilityCategories.AbilityCategory.ATTRIBUTE, LightHealingAbility.class)
            .setDescription("Heals yourself with the power of light")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    private static final ParticleEffect PARTICLES = new LightHealingParticleEffect();

    public LightHealingAbility()
    {
        super(INSTANCE);
        this.setmanaCost(25);
        this.setMaxCooldown(20);
        this.setExperiencePoint(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        //TODO make a special effect just for lighthealing
        player.addEffect(new EffectInstance(Effects.REGENERATION, 80, 3));
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

        return true;
    }
}
