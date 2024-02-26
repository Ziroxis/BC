package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class TaintedCulvertWyrmProjectile extends AbilityProjectileEntity {

    public TaintedCulvertWyrmProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public TaintedCulvertWyrmProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.TAINTED_CULVERT_WYRM.get(), world, player);
        this.setDamage(15);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityHit;
    }

    private void onEntityHit(LivingEntity entity)
    {
        if (!entity.hasEffect(ModEffects.BUBBLED.get()))
        {
            entity.addEffect(new EffectInstance(ModEffects.BUBBLED.get(), 100, 0));
        }
        else
        {
            entity.addEffect(new EffectInstance(ModEffects.BUBBLED.get(), 40, 0));
        }

    }
}
