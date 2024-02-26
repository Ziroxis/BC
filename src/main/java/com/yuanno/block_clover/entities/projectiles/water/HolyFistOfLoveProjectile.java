package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class HolyFistOfLoveProjectile extends AbilityProjectileEntity {

    public HolyFistOfLoveProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public HolyFistOfLoveProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.HOLY_FIST.get(), world, player);
        this.setDamage(10);
        this.setMaxLife(32);
        this.setPhysical(true);
    }
}
