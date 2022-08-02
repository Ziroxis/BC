package com.yuanno.block_clover.entities.projectiles.darkness;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class AvidyaSlashProjectile extends AbilityProjectileEntity {

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
}
