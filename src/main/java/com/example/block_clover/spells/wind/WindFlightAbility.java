package com.example.block_clover.spells.wind;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.PassiveAbility;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModAttributes;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncEntityStatsPacket;
import com.example.block_clover.particles.ParticleEffect;
import com.example.block_clover.particles.anti_magic.DemonStateParticleEffect;
import com.example.block_clover.particles.wind.WindFlightParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;


public class WindFlightAbility extends PassiveAbility {
    public static final WindFlightAbility INSTANCE = new WindFlightAbility();
    public float speed = 0;
    private static final ParticleEffect PARTICLES = new WindFlightParticleEffect();

    public WindFlightAbility()
    {
        super("Wind Flight", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Makes you fly with your wind");
        this.hideInGUI(false);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void duringPassiveEvent(PlayerEntity player)
    {
        /*
        if (player.level.isClientSide)
            return;
         */
        //TODO check if you crash in server or nah
        if (player.isOnGround() || player.isSwimming())
            return;

        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getMana() <= 0)
        {
            return;
        }
        if (player.tickCount % 20 == 0)
        {
            stats.alterMana(-5);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
        }
        if (player.getAttribute(ModAttributes.JUMP_HEIGHT.get()).getValue() < 0)
            return;
        boolean isSprinting = player.isSprinting();
        if (6 > player.getFoodData().getFoodLevel())
            isSprinting = false;
        float maxSpeed = isSprinting ? 2 : 1.25F;
        float acceleration = isSprinting ? 0.014f : 0.007f;
        /*
        if(player.getEffect(ModEffects.FATIGUE_EFFECT.get()) != null)
            maxSpeed /= 1 + Math.min(player.getEffect(ModEffects.FATIGUE_EFFECT.get()).getAmplifier(), 3);
         */


        acceleration *= (this.speed > 0 ? (1 - this.speed / maxSpeed) : 1);
        acceleration = (player.zza > 0 && !(player.verticalCollision || player.horizontalCollision)) ? acceleration : -maxSpeed / 10;
        this.speed = MathHelper.clamp(this.speed + acceleration, acceleration > 0 ? maxSpeed / 5 : 0, maxSpeed);
        float speed = this.speed;


        int d1 = player.isOnGround() ? 1 : 0;
        int d2 = player.zza > 0F ? 1 : 0;
        int d3 = 0;
        int d4 = ((maxSpeed / 3) >= speed || d2 == 0) ? 1 : 0;

        Vector3d vec = player.getLookAngle();
        double x = (vec.x * speed) * (1 - d1) * d2 + d3 * player.getDeltaMovement().z;
        double y = d1 * 0.65f + (vec.y * speed) * (1 - d1) * d2 + d3 * -0.5f + d4 * (Math.cos(player.tickCount / 4f) / 5f);
        double z = (vec.z * speed) * (1 - d1) * d2 + d3 * player.getDeltaMovement().z;
        player.setDeltaMovement(x, y, z);

        if (player.getY() > player.level.getMaxBuildHeight() - 1)
            player.setDeltaMovement(0, -3, 0);

        player.fallDistance = 0;
        player.causeFoodExhaustion(isSprinting ? 0.015F : 0.01F);
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

    }
}
