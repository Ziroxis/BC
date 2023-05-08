package com.yuanno.block_clover.entities.projectiles.light;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class GiantLightBladeProjectile extends AbilityProjectileEntity {

    public GiantLightBladeProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public GiantLightBladeProjectile(World world, LivingEntity player)
    {
        super(LightProjectiles.LIGHT_BLADE.get(), world, player);
        this.setDamage(12);
        this.setArmorPiercing();
        this.setPassThroughEntities();
        this.setMaxLife(128);
        this.setPhysical(false);
    }
}
