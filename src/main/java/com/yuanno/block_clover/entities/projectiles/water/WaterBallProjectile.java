package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.FireBallParticleEffect;
import com.yuanno.block_clover.particles.water.WaterBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterBallProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new WaterBallParticleEffect();


    public WaterBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterBallProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_BALL.get(), world, player);
        this.setDamage(6);
        this.setMaxLife(64);
        this.setPhysical(false);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.level.isClientSide)
            PARTICLES.spawn(this.level, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }
}
