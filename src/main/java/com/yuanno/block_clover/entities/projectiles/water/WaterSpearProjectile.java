package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.entities.projectiles.darkness.DarknessProjectiles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterSpearProjectile extends AbilityProjectileEntity {

    public WaterSpearProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterSpearProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_SPEAR.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(64);
        this.setPhysical(false);
    }
}
