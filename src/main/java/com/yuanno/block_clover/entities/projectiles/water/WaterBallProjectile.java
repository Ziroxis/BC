package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterBallProjectile extends AbilityProjectileEntity {
    public WaterBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterBallProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_BALL.get(), world, player);
        this.setDamage(4);
        this.setMaxLife(64);
        this.setPhysical(false);
    }
}
