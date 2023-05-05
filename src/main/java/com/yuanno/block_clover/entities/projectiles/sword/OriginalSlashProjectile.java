package com.yuanno.block_clover.entities.projectiles.sword;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.FireBallParticleEffect;
import com.yuanno.block_clover.particles.sword.OriginalSlashParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

public class OriginalSlashProjectile extends AbilityProjectileEntity {
    private static final ParticleEffect PARTICLES = new OriginalSlashParticleEffect();

    public OriginalSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public OriginalSlashProjectile(World world, LivingEntity player)
    {
        super(SwordProjectiles.ORIGINAL_SLASH.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(32);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;

    }
    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!this.level.isClientSide)
            PARTICLES.spawn(this.level, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }
}
