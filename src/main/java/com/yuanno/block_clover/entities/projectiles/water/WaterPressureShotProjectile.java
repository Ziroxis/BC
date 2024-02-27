package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.water.WaterBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterPressureShotProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new WaterBallParticleEffect();

    public WaterPressureShotProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterPressureShotProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_PRESSURE_SHOT.get(), world, player);
        this.setDamage(4);
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
