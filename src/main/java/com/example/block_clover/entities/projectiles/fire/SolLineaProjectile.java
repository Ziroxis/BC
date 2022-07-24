package com.example.block_clover.entities.projectiles.fire;

import com.example.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SolLineaProjectile extends AbilityProjectileEntity {

    public SolLineaProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public SolLineaProjectile(World world, LivingEntity player)
    {
        super(FireProjectiles.SOL_LINEA.get(), world, player);
        this.setDamage(15);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        //entity.setSecondsOnFire(5);
    }
}
