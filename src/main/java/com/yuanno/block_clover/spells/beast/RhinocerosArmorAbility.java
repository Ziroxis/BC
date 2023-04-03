package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class RhinocerosArmorAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Rhinoceros armor", AbilityCategories.AbilityCategory.ATTRIBUTE, RhinocerosArmorAbility.class)
            .setDescription("Envelops your skin with mana of a rhinoceros.\nGiving you resistance, auto-step.")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    private static final AttributeModifier RHINOCEROS_RESISTANCE = new AttributeModifier(UUID.fromString("0fe609ae-c5b1-11ed-afa1-0242ac120002"),
            "Rhinoceros Resistance", 3, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier RHINOCEROS_STEP = new AttributeModifier(UUID.fromString("127ff724-c5b1-11ed-afa1-0242ac120002"),
            "Rhinoceros Step", 1, AttributeModifier.Operation.ADDITION);

    public RhinocerosArmorAbility()
    {
        super(INSTANCE);
        this.setmanaCost(8);
        this.setMaxCooldown(10);
        this.setExperiencePoint(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(RHINOCEROS_RESISTANCE);
        player.getAttribute(ModAttributes.FALL_RESISTANCE.get()).addTransientModifier(RHINOCEROS_RESISTANCE);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).addTransientModifier(RHINOCEROS_STEP);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(RHINOCEROS_RESISTANCE);
        player.getAttribute(ModAttributes.FALL_RESISTANCE.get()).removeModifier(RHINOCEROS_RESISTANCE);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(RHINOCEROS_STEP);
        return true;
    }
}
