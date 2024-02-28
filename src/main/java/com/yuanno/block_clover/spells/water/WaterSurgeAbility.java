package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.water.WaterSurgeParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class WaterSurgeAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Surge", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterSurgeAbility.class)
            .setDescription("Create a surge of water, pushing entities away")
            .build();

    public static final ParticleEffect PARTICLES = new WaterSurgeParticleEffect();
    int diameter;

    public WaterSurgeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(30);
        this.setMaxCooldown(20);

        this.onUseEvent = this::onUseEvent;
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        diameter = 10;

        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, diameter);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 4);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);

                    // Calculate direction from the player to the entity
                    double dx = entity.getX() - player.getX();
                    double dy = entity.getY() - player.getY();
                    double dz = entity.getZ() - player.getZ();

                    // Normalize the direction to get a unit vector
                    double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    dx /= distance;
                    dy /= distance;
                    dz /= distance;

                    // Apply a velocity to the entity in the direction away from the player
                    double speed = 2.0; // Adjust this value to control how far the entity is pushed
                    entity.setDeltaMovement(dx * speed, dy * speed, dz * speed);

                }
            });
        }
        return true;
    }
}
