package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.entities.projectiles.fire.FireProjectiles;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.FireBallParticleEffect;
import com.yuanno.block_clover.particles.water.WaterBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class CascadeDestroyerProjectile extends AbilityProjectileEntity {
    private static final ParticleEffect PARTICLES = new WaterBallParticleEffect();

    public CascadeDestroyerProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public CascadeDestroyerProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.CASCADE_DESTROYER.get(), world, player);
        this.setDamage(15);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.setPassThroughEntities();
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
        //entity.setSecondsOnFire(5);
    }
}
