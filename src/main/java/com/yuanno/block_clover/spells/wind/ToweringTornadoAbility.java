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
    public ToweringTornadoAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(15);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(30);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 12F, LivingEntity.class);
        entities.remove(player);

        entities.forEach(entityi ->
        {
            Vector3d speed = Beapi.Propulsion(player, 5, 5, 5);
            entityi.setDeltaMovement(speed.x, speed.y, speed.z);
            entityi.hurtMarked = true;
            entityi.hasImpulse = true;


            entityi.hurt(ModDamageSource.causeAbilityDamage(player, this), 7);
        });

        if (player.level instanceof ServerWorld)
        {
            ((ServerWorld) player.level).sendParticles(ParticleTypes.SPIT, player.getX(), player.getY(), player.getZ(), (int) 100, 3, 2, 3, 1);
        }

        return true;
    }
}
