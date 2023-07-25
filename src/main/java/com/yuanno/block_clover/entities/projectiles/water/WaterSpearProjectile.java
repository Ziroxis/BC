package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.entities.projectiles.darkness.DarknessProjectiles;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.water.WaterBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterSpearProjectile extends AbilityProjectileEntity {
    private static final ParticleEffect PARTICLES = new WaterBallParticleEffect();


    public WaterSpearProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterSpearProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_SPEAR.get(), world, player);
        this.setDamage(15);
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
