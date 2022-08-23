package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class PointBlankDragonProjectile extends AbilityProjectileEntity {
    public PointBlankDragonProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public PointBlankDragonProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.POINT_BLANK_DRAGON.get(), world, player);
        this.setDamage(20);
        this.setMaxLife(32);
        this.setPhysical(false);
    }
}
