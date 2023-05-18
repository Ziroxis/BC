package com.yuanno.block_clover.spells.lightning;

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
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ThunderDischargeAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder Discharge", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderDischargeAbility.class)
            .setDescription("Discharges all your electricity.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public static final ParticleEffect PARTICLES = new DischargeParticleEffect();
    int diameter;
    public ThunderDischargeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(50);
        this.setMaxCooldown(25);
        this.setExperiencePoint(25);
        this.setEvolutionCost(100);
        this.onUseEvent = this::onUseEvent;
    }
    
    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (!this.isEvolved())
            diameter = 10;
        else
            diameter = 16;
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, diameter + (float) stats.getLevel() / 2);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    if (!this.isEvolved()) {
                        ((LivingEntity) entity).addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 80, 0));
                        entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 10);
                    }
                    else
                    {
                        ((LivingEntity) entity).addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 120, 0));
                        entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 14);

                    }
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }            });
        }
        return true;
    }
}
