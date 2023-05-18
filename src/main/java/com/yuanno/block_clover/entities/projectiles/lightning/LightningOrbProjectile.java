package com.yuanno.block_clover.entities.projectiles.lightning;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.LightningOrbParticleEffect;
import com.yuanno.block_clover.particles.wind.WindBladeParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class LightningOrbProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new LightningOrbParticleEffect();

    public LightningOrbProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public LightningOrbProjectile(World world, LivingEntity player)
    {
        super(LightningProjectiles.LIGHTNING_ORB.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(128);
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
