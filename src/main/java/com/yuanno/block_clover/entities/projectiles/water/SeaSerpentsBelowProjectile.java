package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SeaSerpentsBelowProjectile extends AbilityProjectileEntity {

    public SeaSerpentsBelowProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public SeaSerpentsBelowProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.SEA_SERPENTS_BELOW.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(32);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityHit;
    }

    private void onEntityHit(LivingEntity entity)
    {
        if (!entity.hasEffect(Effects.POISON))
        {
            entity.addEffect(new EffectInstance(Effects.POISON, 100, 2));
        }
        else
        {
            entity.addEffect(new EffectInstance(Effects.POISON, 100, 2));
        }

    }
}
