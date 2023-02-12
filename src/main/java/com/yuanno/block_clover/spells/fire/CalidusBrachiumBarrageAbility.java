package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

public class CalidusBrachiumBarrageAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Calidus brachium barrage", AbilityCategories.AbilityCategory.ATTRIBUTE, CalidusBrachiumBarrageAbility.class)
            .setDescription("Targeting a player in range will setup a mana zone\n Afterwards the player will get hit with multiple fiery punches")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    Entity entity;
    public CalidusBrachiumBarrageAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(0);
        this.setmanaCost(0);
        this.onStartContinuityEvent = this::onStartEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        RayTraceResult rayTraceBlocksAndEntities = Beapi.rayTraceBlocksAndEntities(player, 8);
        if (rayTraceBlocksAndEntities instanceof EntityRayTraceResult) {
            EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) rayTraceBlocksAndEntities;
            if (entityRayTraceResult.getEntity() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity) entityRayTraceResult.getEntity()).isPhysical())
                return true;

            entity = ((EntityRayTraceResult) rayTraceBlocksAndEntities).getEntity();
        }
        return entity != null;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        int randX = random.nextInt(16);
        int randY = random.nextInt(4);
        int randZ = random.nextInt(16);
        boolean x = false;
        boolean y = false;
        if (Beapi.RNGboolean())
            x = true;
        if (Beapi.RNGboolean())
            y = true;
        FireBallProjectile projectile = new FireBallProjectile(player.level, player);
        if (x && y)
            projectile.setPos(entity.getX() + randX, entity.getY() + 3 + randY, entity.getZ() + randZ);
        else if (x)
            projectile.setPos(entity.getX() + randX, entity.getY() + 3 + randY, entity.getZ() - randZ);
        else if (y)
            projectile.setPos(entity.getX() - randX, entity.getY() + 3 + randY, entity.getZ() + randZ);
        else
            projectile.setPos(entity.getX() - randX, entity.getY() + 3 + randY, entity.getZ() - randZ);
        projectile.push((entity.getX() - projectile.getX()) * 0.3, (entity.getY() - projectile.getY()) * 0.3, (entity.getZ() - projectile.getZ()) * 0.3);
        player.level.addFreshEntity(projectile);

        if (timer >= 120)
            this.stopContinuity(player);
    }


}
