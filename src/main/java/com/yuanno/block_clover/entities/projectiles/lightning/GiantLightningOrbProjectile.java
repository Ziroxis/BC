package com.yuanno.block_clover.entities.projectiles.lightning;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.GiantFirebalParticleEffect;
import com.yuanno.block_clover.particles.lightning.GiantLightningOrbParticleEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class GiantLightningOrbProjectile extends AbilityProjectileEntity {
    private static final ParticleEffect PARTICLES = new GiantLightningOrbParticleEffect();


    public GiantLightningOrbProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public GiantLightningOrbProjectile(World world, LivingEntity player)
    {
        super(LightningProjectiles.GIANT_LIGHTNING_ORB.get(), world, player);
        this.setDamage(10);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;

    }
    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(ModEffects.ELECTROCUTED.get()))
            entity.addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 30, 0));
        if (!entity.level.isClientSide)
            PARTICLES.spawn(entity.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

    }
}
