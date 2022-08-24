package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterDragonProjectile extends AbilityProjectileEntity {

    public WaterDragonProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterDragonProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_DRAGON.get(), world, player);
        this.setDamage(20);
        this.setMaxLife(96);
        this.setPhysical(false);
    }}
