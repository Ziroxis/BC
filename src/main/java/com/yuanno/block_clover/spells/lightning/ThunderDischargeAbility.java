package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
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
    
    public static final ThunderDischargeAbility INSTANCE = new ThunderDischargeAbility();
    public static final ParticleEffect PARTICLES = new DischargeParticleEffect();

    public ThunderDischargeAbility()
    {
        super("Thunder Discharge", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Discharges all your electricity!");
        this.setmanaCost(50);
        this.setMaxCooldown(25);
        this.setExperiencePoint(25);
        this.onUseEvent = this::onUseEvent;
    }
    
    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 10 + (float) stats.getLevel() / 2);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    ((LivingEntity) entity).addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 80, 0));
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 10);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }            });
        }
        return true;
    }
}
