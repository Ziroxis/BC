package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.earth.EarthQuakeParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class EarthQuakeAbility extends Ability {
    public static final EarthQuakeAbility INSTANCE = new EarthQuakeAbility();
    private static final ParticleEffect PARTICLES = new EarthQuakeParticleEffect();

    public EarthQuakeAbility()
    {
        super("Earth Quake", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Causes an earthquake, damaging every enemy on the ground around you");
        this.setmanaCost(40);
        this.setMaxCooldown(25);
        this.setExperiencePoint(20);
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

                if (entity.isOnGround())
                {
                    entity.setDeltaMovement(0, 5, 0);
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 17);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }
            });
        }
        return true;
    }
}
