package com.example.block_clover.entities.projectiles.fire;

import com.example.block_clover.api.ability.AbilityProjectileEntity;
import com.example.block_clover.entities.projectiles.light.LightProjectiles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FireBallProjectile extends AbilityProjectileEntity {

    public FireBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public FireBallProjectile(World world, LivingEntity player)
    {
        super(FireProjectiles.FIRE_BALL.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.setSecondsOnFire(5);
    }
}
