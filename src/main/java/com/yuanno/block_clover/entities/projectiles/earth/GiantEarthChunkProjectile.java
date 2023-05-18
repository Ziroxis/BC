package com.yuanno.block_clover.entities.projectiles.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.api.ability.sorts.ExplosionAbility;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.CommonExplosionParticleEffect;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class GiantEarthChunkProjectile extends AbilityProjectileEntity {

    public GiantEarthChunkProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public GiantEarthChunkProjectile(World world, LivingEntity player)
    {
        super(EarthProjectiles.GIANT_EARTH_CHUNK.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
        this.onBlockImpactEvent = this::onHitImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        List<LivingEntity> list = Beapi.getEntitiesNear(entity.blockPosition(), entity.level, 10, LivingEntity.class);
        list.remove(this.getOwner());

        list.forEach(entityHit ->
        {
            entity.setDeltaMovement(0, 3, 0);
            entity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 60, 0));
            entity.hurt(ModDamageSource.causeAbilityDamage((LivingEntity) this.getOwner(), EarthChunkAbility.INSTANCE.createAbility(), "player"), 6);
        });
    }

    private void onHitImpactEvent(BlockPos hit)
    {
        LivingEntity livingEntity = (LivingEntity) this.getOwner();
        World world = livingEntity.level;
        List<LivingEntity> list = Beapi.getEntitiesAround(hit, world, 10, LivingEntity.class);
        list.remove(this.getOwner());

        list.forEach(entityHit ->
        {
            entityHit.setDeltaMovement(0, 3, 0);
            entityHit.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 60, 0));
            entityHit.hurt(ModDamageSource.causeAbilityDamage((LivingEntity) this.getOwner(), EarthChunkAbility.INSTANCE.createAbility(), "player"), 6);
        });
    }
}
