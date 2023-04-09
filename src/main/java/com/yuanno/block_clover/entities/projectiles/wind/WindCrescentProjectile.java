package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.wind.WindBladeParticleEffect;
import com.yuanno.block_clover.particles.wind.WindCrescentParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WindCrescentProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new WindCrescentParticleEffect();


    public WindCrescentProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WindCrescentProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.WIND_CRESCENT.get(), world, player);
        this.setDamage(10);
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
