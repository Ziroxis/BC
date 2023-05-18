package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ToweringTornadoAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Towering Tornado", AbilityCategories.AbilityCategory.ATTRIBUTE, ToweringTornadoAbility.class)
            .setDescription("Pushes enemies away from you with wind")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    int diameter = 12;
    int propulsion = 5;
    int damage = 7;
    public ToweringTornadoAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(15);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(30);
        this.setEvolutionCost(50);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (this.isEvolved())
        {
            diameter = 20;
            propulsion = 7;
            damage = 10;
        }
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, diameter, LivingEntity.class);
        entities.remove(player);

        entities.forEach(entityi ->
        {
            Vector3d speed = Beapi.Propulsion(player, propulsion, propulsion, propulsion);
            entityi.setDeltaMovement(speed.x, speed.y, speed.z);
            entityi.hurtMarked = true;
            entityi.hasImpulse = true;


            entityi.hurt(ModDamageSource.causeAbilityDamage(player, this), damage);
        });

        if (player.level instanceof ServerWorld)
        {
            ((ServerWorld) player.level).sendParticles(ParticleTypes.SPIT, player.getX(), player.getY(), player.getZ(), (int) 100, 3, 2, 3, 1);
        }

        return true;
    }
}
