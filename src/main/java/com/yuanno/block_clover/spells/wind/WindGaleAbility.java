package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.entities.projectiles.wind.WindGaleProjectile;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class WindGaleAbility extends ChargeableAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Gale", AbilityCategories.AbilityCategory.ATTRIBUTE, WindGaleAbility.class)
            .setDescription("Concentrate the wind while being unable to move.\nRight after, shooting a wind blast.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();    private boolean cancelled = false;

    public WindGaleAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(12);
        this.setMaxChargeTime(5);
        this.setmanaCost(30);
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
        if (charge < 20 * 5)
            return false;

        WindGaleProjectile projectile = new WindGaleProjectile(player.level, player);
        projectile.setDamage(charge / 10f);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 0.1f);
        player.level.addFreshEntity(projectile);
        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        int cooldown = (int) Math.round(charge / 20.0) + 5;
        this.setMaxCooldown(cooldown);
        return true;
    }
}
