package com.example.block_clover.spells.earth;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.ChargeableAbility;
import com.example.block_clover.entities.projectiles.earth.EarthChargeProjectile;
import com.example.block_clover.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class EarthChargeAbility extends ChargeableAbility {


    public static final EarthChargeAbility INSTANCE = new EarthChargeAbility();
    private boolean cancelled = false;

    public EarthChargeAbility()
    {
        super("Earth Charge", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Concetrates everything on throwing one big chunk of earth");
        this.setMaxCooldown(15);
        this.setMaxChargeTime(10);
        this.setmanaCost(20);
        this.setExperiencePoint(20);
        this.setCancelable();

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

        if (charge < 20 * 10)
            return false;

        EarthChargeProjectile projectile = new EarthChargeProjectile(player.level, player);
        projectile.setDamage(charge / 10f);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1, 0.1f);
        player.level.addFreshEntity(projectile);
        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        int cooldown = (int) Math.round(charge / 20.0) + 5;
        this.setMaxCooldown(cooldown);
        return true;
    }
}
