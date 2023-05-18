package com.yuanno.block_clover.entities.projectiles.sealing;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.DischargeParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.List;

public class EvolvedSealingProjectile extends AbilityProjectileEntity {
    public static final ParticleEffect PARTICLES = new DischargeParticleEffect();

    public EvolvedSealingProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public EvolvedSealingProjectile(World world, LivingEntity player)
    {
        super(SealingProjectiles.EVOLVED_SEALING_PROJECTILE.get(), world, player);
        this.setDamage(3);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.hasEffect(ModEffects.SEALING.get()))
            entity.addEffect(new EffectInstance(ModEffects.SEALING.get(), 80, 0));
        List<LivingEntity> entities = Beapi.getEntitiesAround(entity.blockPosition(), entity.level, 12);
        if (entities.contains(this.getOwner()))
            entities.remove(this.getOwner());
        entities.forEach(entityInList -> {
            if (!entityInList.hasEffect(ModEffects.SEALING.get()))
                entityInList.addEffect(new EffectInstance(ModEffects.SEALING.get(), 60, 0));

        });
        PARTICLES.spawn(entity.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);


    }
}
