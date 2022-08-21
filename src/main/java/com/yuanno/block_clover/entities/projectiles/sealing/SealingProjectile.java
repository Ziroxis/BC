package com.yuanno.block_clover.entities.projectiles.sealing;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class SealingProjectile extends AbilityProjectileEntity {

    public SealingProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public SealingProjectile(World world, LivingEntity player)
    {
        super(SealingProjectiles.SEALING_PROJECTILE.get(), world, player);
        this.setDamage(3);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(ModEffects.SEALING.get()))
            entity.addEffect(new EffectInstance(ModEffects.SEALING.get(), 60, 0));
    }
}
