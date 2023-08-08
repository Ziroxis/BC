package com.yuanno.block_clover.entities.projectiles.ice;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class IceBallProjectile extends AbilityProjectileEntity {

    public IceBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public IceBallProjectile(World world, LivingEntity player)
    {
        super(IceProjectiles.ICE_BALL.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(ModEffects.FREEZE.get()))
            entity.addEffect(new EffectInstance(ModEffects.FREEZE.get(), 30, 0));
    }
}
