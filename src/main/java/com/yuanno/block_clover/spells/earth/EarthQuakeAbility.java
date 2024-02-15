package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
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
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Earth quake", AbilityCategories.AbilityCategory.ATTRIBUTE, EarthQuakeAbility.class)
            .setDescription("Causes an earthquake, damaging every enemy on the ground around you")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private static final ParticleEffect PARTICLES = new EarthQuakeParticleEffect();
    int damage;
    int diameter;
    int jump;
    public EarthQuakeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(40);
        this.setMaxCooldown(25);
        this.setEvolutionCost(50);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        damage = 12;
        diameter = 8;
        jump = 5;
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, diameter + (float) stats.getLevel() / 2);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity.isOnGround())
                {
                    entity.setDeltaMovement(0, jump, 0);
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this), damage);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }
            });
        }
        return true;
    }
}
