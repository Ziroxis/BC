package com.yuanno.block_clover.entities.projectiles.fire;

import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.api.ability.sorts.ExplosionAbility;
import com.yuanno.block_clover.particles.CommonExplosionParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GiantFireBallProjectile extends AbilityProjectileEntity {

    public GiantFireBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public GiantFireBallProjectile(World world, LivingEntity player)
    {
        super(FireProjectiles.GIANT_FIRE_BALL.get(), world, player);
        this.setDamage(10);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
        this.onBlockImpactEvent = this::onHitImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.setSecondsOnFire(7);
        ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.level, entity.getX(), entity.getY(), entity.getZ(), 3);
        explosion.setStaticDamage(5);
        explosion.setExplosionSound(true);
        explosion.setDamageOwner(false);
        explosion.setDestroyBlocks(true);
        explosion.setFireAfterExplosion(false);
        explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
        explosion.setDamageEntities(true);
        explosion.doExplosion();
    }

    private void onHitImpactEvent(BlockPos hit)
    {
        ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.level, hit.getX(), hit.getY(), hit.getZ(), 3);
        explosion.setStaticDamage(5);
        explosion.setExplosionSound(true);
        explosion.setDamageOwner(false);
        explosion.setDestroyBlocks(true);
        explosion.setFireAfterExplosion(false);
        explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
        explosion.setDamageEntities(true);
        explosion.doExplosion();
    }
}
