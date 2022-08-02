package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightHealingParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LightHealingAbility extends Ability {
    public static final LightHealingAbility INSTANCE = new LightHealingAbility();
    private static final ParticleEffect PARTICLES = new LightHealingParticleEffect();

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
        //TODO make a special effect just for lighthealing
        player.addEffect(new EffectInstance(Effects.REGENERATION, 80, 3));
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

        return true;
    }
}
