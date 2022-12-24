package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightMovementParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import java.util.List;

public class ArrowsOfJudgement extends Ability {
    public static final ArrowsOfJudgement INSTANCE = new ArrowsOfJudgement();
    private static final ParticleEffect PARTICLES = new LightMovementParticleEffect();

    public ArrowsOfJudgement()
    {
        super("Arrows of judgement", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a massive amount of arrows from above towards your enemies");
        this.setmanaCost(0);
        this.setMaxCooldown(180);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        BlockPos blockPos = Beapi.rayTraceBlockSafe(player, 16);
        List<LivingEntity> entities = Beapi.getEntitiesAround(blockPos, player.level, 64, LivingEntity.class);
        entities.forEach(livingEntity -> {
            LightBladeProjectile projectile = new LightBladeProjectile(player.level, player);
            projectile.setDamage(20);
            player.level.addFreshEntity(projectile);
            projectile.setPos(livingEntity.getX(), livingEntity.getY() + 10, livingEntity.getZ());
            PARTICLES.spawn(player.level, livingEntity.getX(), livingEntity.getY() + 10, livingEntity.getZ(), 0, 0, 0);
            projectile.shoot(0, -180, 0, 1.5f, 0);
        });

        return true;
    }
}
