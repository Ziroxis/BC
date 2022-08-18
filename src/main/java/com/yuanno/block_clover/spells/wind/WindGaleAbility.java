package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.entities.projectiles.earth.EarthChargeProjectile;
import com.yuanno.block_clover.entities.projectiles.wind.WindGaleProjectile;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

public class WindGaleAbility extends ChargeableAbility {
    public static final WindGaleAbility INSTANCE = new WindGaleAbility();
    private boolean cancelled = false;

    public WindGaleAbility()
    {
        super("Wind Gale", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("You charge up a wind gale and shoots it.");
        this.setMaxCooldown(10);
        this.setmanaCost(35);
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
        projectile.setDamage(charge / 5f);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1, 0.1f);
        player.level.addFreshEntity(projectile);
        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        int cooldown = (int) Math.round(charge / 20.0) + 5;
        this.setMaxCooldown(cooldown);
        return true;
    }
}