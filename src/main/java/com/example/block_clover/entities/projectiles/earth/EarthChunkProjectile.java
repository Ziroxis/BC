package com.example.block_clover.entities.projectiles.earth;

import com.example.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class EarthChunkProjectile extends AbilityProjectileEntity {

    public EarthChunkProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public EarthChunkProjectile(World world, LivingEntity player)
    {
        super(EarthProjectiles.EARTH_CHUNK.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(64);
        this.setPhysical(false);
    }
}
