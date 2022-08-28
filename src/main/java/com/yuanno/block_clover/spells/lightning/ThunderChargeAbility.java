package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.GlovesParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ThunderChargeAbility extends ChargeableAbility implements IMultiTargetAbility, IParallelContinuousAbility {

    public static final ThunderChargeAbility INSTANCE = new ThunderChargeAbility();
    private boolean cancelled = false;
    private ParticleEffect PARTICLES = new GlovesParticleEffect();

    public ThunderChargeAbility()
    {
        super("Thunder Charge", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Charges up before dashing forward with speed dealing massive damage to enemies in your way.");
        this.setMaxCooldown(15);
        this.setMaxChargeTime(7);
        this.setmanaCost(20);
        this.setExperiencePoint(20);

        this.setCancelable();

        this.onStartChargingEvent = this::onStartChargingEvent;
        this.onEndChargingEvent = this::onEndChargingEvent;
        this.duringChargingEvent = this::duringChargingEvent;
        this.duringCooldownEvent = this::duringCooldown;

    }

    private boolean onStartChargingEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        ThunderGodBootsAbility thunderGodBootsAbility = abilityProps.getEquippedAbility(ThunderGodBootsAbility.INSTANCE);
        if (thunderGodBootsAbility != null && thunderGodBootsAbility.isContinuous())
        {
            this.clearTargets();
            this.cancelled = false;
            return true;
        }
        else
        {
            player.sendMessage(new StringTextComponent("Need to put on your thunder god boots!"), Util.NIL_UUID);
            return false;
        }
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
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);


        int charge = this.getMaxChargeTime() - this.getChargeTime();

        if (charge < 20 * 5)
            return false;
        Vector3d speed = Beapi.propulsion(player, 15, 15);
        player.setDeltaMovement(speed.x, 0.1, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));

        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        int cooldown = (int) Math.round(charge / 20.0) + 5;
        this.setMaxCooldown(cooldown);
        return true;
    }

    private void duringCooldown(PlayerEntity player, int cooldownTimer)
    {
        if (this.canDealDamage())
        {

            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, 3, LivingEntity.class);
            list.remove(player);

            list.forEach(entity ->
            {
                PARTICLES.spawn(entity.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), 15);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9 * 5 && !this.cancelled;
    }
}
