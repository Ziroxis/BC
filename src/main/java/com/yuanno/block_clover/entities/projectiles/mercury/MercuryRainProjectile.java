package com.yuanno.block_clover.entities.projectiles.mercury;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class MercuryRainProjectile extends AbilityProjectileEntity {

    public MercuryRainProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public MercuryRainProjectile(World world, LivingEntity player)
    {
        super(MercuryProjectiles.MERCURY_RAIN.get(), world, player);
        this.setDamage(6);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(Effects.POISON))
            entity.addEffect(new EffectInstance(Effects.POISON, 60, 1));
    }
}
