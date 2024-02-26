package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterDragonProjectile extends AbilityProjectileEntity {

    private LivingEntity grabbedEntity = null;
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
        this.setPassThroughEntities();
        this.setPassThroughBlocks();
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity livingEntity)
    {
        if (this.grabbedEntity == null) {
            this.grabbedEntity = livingEntity;
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 1.0D, this.getDeltaMovement().z);
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.grabbedEntity != null && this.grabbedEntity.isAlive())
        {
            this.grabbedEntity.setPos(this.getX(), this.getY(), this.getZ());
        }
    }

    public LivingEntity getGrabbedEntity()
    {
        return this.grabbedEntity;
    }
}
