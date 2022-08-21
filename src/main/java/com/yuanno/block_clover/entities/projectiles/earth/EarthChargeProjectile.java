package com.yuanno.block_clover.entities.projectiles.earth;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class EarthChargeProjectile extends AbilityProjectileEntity {

    public EarthChargeProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public EarthChargeProjectile(World world, LivingEntity player)
    {
        super(EarthProjectiles.EARTH_CHARGE.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(64);
        this.setPhysical(false);
    }
}
