package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
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
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ThunderGodBootsAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ThunderGodBootsAbility INSTANCE = new ThunderGodBootsAbility();
    private static final AttributeModifier LIGHTNING_SPEED = new AttributeModifier(UUID.fromString("47642942-fec9-11ec-b939-0242ac120002"),
            "Lightning Speed", 0.5, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier LIGHTNING_STEP = new AttributeModifier(UUID.fromString("25c5b788-fec9-11ec-b939-0242ac120002"),
            "Lightning Step", 1, AttributeModifier.Operation.ADDITION);

    private static final ParticleEffect PARTICLES = new BootsParticleEffect();
    public ThunderGodBootsAbility()
    {
        super("Thunder God Boots", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("The user gets speed by enveloping himself with lightning boots");
        this.setmanaCost(5);
        this.setMaxCooldown(5);
        this.setExperiencePoint(7);
        this.setExperienceGainLevelCap(10);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::onDuringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        propsEntity.alterMana(-15);
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
}
