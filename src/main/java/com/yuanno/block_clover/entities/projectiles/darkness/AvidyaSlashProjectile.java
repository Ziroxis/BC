package com.yuanno.block_clover.entities.projectiles.darkness;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.darkness.AvidyaSlashparticleEffect;
import com.yuanno.block_clover.particles.fire.FireBallParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class AvidyaSlashProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new AvidyaSlashparticleEffect();

    public AvidyaSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public AvidyaSlashProjectile(World world, LivingEntity player)
    {
        super(DarknessProjectiles.AVIDYA_SLASH.get(), world, player);
        this.setDamage(7);
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
