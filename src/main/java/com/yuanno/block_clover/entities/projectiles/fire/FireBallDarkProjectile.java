package com.yuanno.block_clover.entities.projectiles.fire;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.FireBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FireBallDarkProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new FireBallParticleEffect();

    public FireBallDarkProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public FireBallDarkProjectile(World world, LivingEntity player)
    {
        super(FireProjectiles.FIRE_BALL_DARK.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.level.isClientSide)
            PARTICLES.spawn(this.level, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }
    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.setSecondsOnFire(8);
    }
}
