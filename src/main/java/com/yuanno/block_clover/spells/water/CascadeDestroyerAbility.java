package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.entities.projectiles.water.CascadeDestroyerProjectile;
import com.yuanno.block_clover.entities.projectiles.water.WaterSpearProjectile;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.Random;

public class CascadeDestroyerAbility extends ChargeableAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Cascade Destroyer", AbilityCategories.AbilityCategory.ATTRIBUTE, CascadeDestroyerAbility.class)
            .setDescription("Shoots multiple cascades, depending on charge time.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private boolean cancelled = false;

    public CascadeDestroyerAbility()
    {
        super(INSTANCE);
        this.setCancelable();
        this.setMaxChargeTime(5);

        this.onStartChargingEvent = this::onStartChargingEvent;
        this.onEndChargingEvent = this::onEndChargingEvent;
        this.duringChargingEvent = this::duringChargingEvent;
    }

    private boolean onStartChargingEvent(PlayerEntity player)
    {
        this.cancelled = false;
        return true;
    }

    private void duringChargingEvent(PlayerEntity player, int chargeTimer)
    {
        if (player.invulnerableTime > 0)
        {
            this.cancelled = true;
            this.stopCharging(player);
        }
        player.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 200, 10));
    }

    private boolean onEndChargingEvent(PlayerEntity player)
    {
        if (this.cancelled)
            return true;

        int charge = this.getMaxChargeTime() - this.getChargeTime();

        if (charge < 20 * 5)
            return false;

        // Calculate the number of projectiles to create
        int numProjectiles = charge / 20;

        // Create the projectiles
        for (int i = 0; i < numProjectiles; i++) {
            // Calculate an offset for the projectile's position
            // Create a Random instance
            Random random = new Random();

            // Calculate a random offset for the projectile's position
            double offsetX = (random.nextDouble() - 0.5) * 4; // random value between -1 and 1
            double offsetY = (random.nextDouble() - 0.5) * 4; // random value between -1 and 1
            double offsetZ = (random.nextDouble() - 0.5) * 4; // random value between -1 and 1

            // Create the projectile
            CascadeDestroyerProjectile waterSpearProjectile = new CascadeDestroyerProjectile(player.level, player);
            waterSpearProjectile.setPos(waterSpearProjectile.getX() + offsetX, waterSpearProjectile.getY() + offsetY, waterSpearProjectile.getZ() + offsetZ);
            waterSpearProjectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2, 8f);
            player.level.addFreshEntity(waterSpearProjectile);
        }

        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        int cooldown = (int) Math.round(charge / 20.0) + 5;
        this.setMaxCooldown(cooldown);
        return true;
    }
}
