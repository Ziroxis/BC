package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.DischargeParticleEffect;
import com.yuanno.block_clover.particles.sealing.SealingWaveParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class SealingWaveAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Sealing Wave", AbilityCategories.AbilityCategory.ATTRIBUTE, SealingWaveAbility.class)
            .setDescription("Seals in place and disables spells in a wave around you, dealing slight damage.")
            .setDamageKind(AbilityDamageKind.CURSE)
            .build();
    public static final ParticleEffect PARTICLES = new SealingWaveParticleEffect();
    public SealingWaveAbility()
    {
        super(INSTANCE);
        this.setmanaCost(50);
        this.setMaxCooldown(30);
        this.setExperiencePoint(45);
        this.onUseEvent = this::onUseEvent;
    }
    
    public boolean onUseEvent(PlayerEntity player)
    {

        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 10);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    ((LivingEntity) entity).addEffect(new EffectInstance(ModEffects.SEALING.get(), 80, 0));
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 6);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }
            });
        }
        return true;
    }
}
