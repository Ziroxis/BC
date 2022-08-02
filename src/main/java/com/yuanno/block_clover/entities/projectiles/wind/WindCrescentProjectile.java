package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WindCrescentProjectile extends AbilityProjectileEntity {
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
}
