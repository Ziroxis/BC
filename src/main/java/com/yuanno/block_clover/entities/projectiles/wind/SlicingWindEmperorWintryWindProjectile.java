package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.wind.WindBladeParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SlicingWindEmperorWintryWindProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new WindBladeParticleEffect();

    public SlicingWindEmperorWintryWindProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public SlicingWindEmperorWintryWindProjectile(World world, LivingEntity livingEntity)
    {
        super(WindProjectiles.WIND_SWORD.get(), world, livingEntity);
        this.setDamage(10);
        this.setMaxLife(64);
        this.setPassThroughEntities();
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.level.isClientSide)
            PARTICLES.spawn(this.level, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }


}
