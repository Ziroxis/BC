package com.example.block_clover.entities.projectiles.wind;

import com.example.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WindBladeProjectile extends AbilityProjectileEntity {

    public WindBladeProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WindBladeProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.WIND_BLADE.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(32);
        this.setPhysical(false);
    }
}
