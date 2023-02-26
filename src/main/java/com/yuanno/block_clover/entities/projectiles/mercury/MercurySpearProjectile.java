package com.yuanno.block_clover.entities.projectiles.mercury;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class MercurySpearProjectile extends AbilityProjectileEntity {

    public MercurySpearProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public MercurySpearProjectile(World world, LivingEntity player)
    {
        super(MercuryProjectiles.MERCURY_SPEAR.get(), world, player);
        this.setDamage(10);
        this.setMaxLife(164);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(Effects.POISON))
            entity.addEffect(new EffectInstance(Effects.POISON, 60, 0));
    }
}
