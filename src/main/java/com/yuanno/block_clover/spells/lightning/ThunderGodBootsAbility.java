package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.curios.network.NetworkHandler;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.BootsParticleEffect;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ThunderGodBootsAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder God boots", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderGodBootsAbility.class)
            .setDescription("The user gets speed by enveloping himself with lightning boots.")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    private static final AttributeModifier LIGHTNING_SPEED = new AttributeModifier(UUID.fromString("47642942-fec9-11ec-b939-0242ac120002"),
            "Lightning Speed", 0.3, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier LIGHTNING_STEP = new AttributeModifier(UUID.fromString("25c5b788-fec9-11ec-b939-0242ac120002"),
            "Lightning Step", 1, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier LIGHTNING_JUMP = new AttributeModifier(UUID.fromString("99e2d1f6-3b80-11ee-be56-0242ac120002"),
            "Lightning Jump", 1, AttributeModifier.Operation.ADDITION);
    private static final ParticleEffect PARTICLES = new BootsParticleEffect();
    public ThunderGodBootsAbility()
    {
        super(INSTANCE);
        this.setmanaCost(5);
        this.setMaxCooldown(5);
        this.setExperiencePoint(7);
        this.setExperienceGainLevelCap(10);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::onDuringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
        this.onStopContinuityEvent = this::onStopContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(LIGHTNING_SPEED);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).addTransientModifier(LIGHTNING_STEP);

        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    private void onDuringContinuityEvent(PlayerEntity player, int timer)
    {
        if (player.isOnGround())
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(LIGHTNING_SPEED);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(LIGHTNING_STEP);

        return true;
    }

    private boolean onStopContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(LIGHTNING_SPEED);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(LIGHTNING_STEP);
        return true;
    }
}
