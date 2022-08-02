package com.yuanno.block_clover.entities.projectiles.lightning;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class LightningOrbProjectile extends AbilityProjectileEntity {

    public LightningOrbProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public LightningOrbProjectile(World world, LivingEntity player)
    {
        super(LightningProjectiles.LIGHTNING_ORB.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(128);
        this.setPhysical(false);
    }
}
