package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WindGaleProjectile extends AbilityProjectileEntity {

    public WindGaleProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WindGaleProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.WIND_GALE.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(64);
        this.setPhysical(false);
    }
}
