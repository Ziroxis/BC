package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class JaguarsHunt extends ContinuousAbility implements IParallelContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Jaguars hunt", AbilityCategories.AbilityCategory.ATTRIBUTE, JaguarsHunt.class)
            .setDescription("Gives you the prolonged speed of a jaguar")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();

    private static final AttributeModifier JAGUAR_SPEED = new AttributeModifier(UUID.fromString("74203b02-d21c-11ed-afa1-0242ac120002"),
            "Jaguar speed", 2, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier JAGUAR_STEP = new AttributeModifier(UUID.fromString("7846ec3c-ed81-11ed-a05b-0242ac120003"),
            "Jaguar step", 1, AttributeModifier.Operation.ADDITION);

    public JaguarsHunt()
    {
        super(INSTANCE);
        this.setmanaCost(8);
        this.setMaxCooldown(10);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(40);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(JAGUAR_SPEED);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).addTransientModifier(JAGUAR_STEP);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(JAGUAR_SPEED);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(JAGUAR_STEP);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }
}
