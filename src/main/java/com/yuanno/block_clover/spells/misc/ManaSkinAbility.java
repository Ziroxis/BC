package com.yuanno.block_clover.spells.misc;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ManaSkinAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ManaSkinAbility INSTANCE = new ManaSkinAbility();
    private static final AttributeModifier MANA_RESISTANCE = new AttributeModifier(UUID.fromString("6a626026-1bca-11ed-861d-0242ac120002"),
            "Mana Resistance", 1, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier MANA_STEP = new AttributeModifier(UUID.fromString("b6abbc2a-1bca-11ed-861d-0242ac120002"),
            "Mana Step", 1, AttributeModifier.Operation.ADDITION);

    public ManaSkinAbility()
    {
        super("Mana Skin", AbilityCategories.AbilityCategory.MISCELLANEOUS);
        this.setDescription("Envelops your skin with mana.\nGiving you resistance, auto-step and fall resistance");
        this.setmanaCost(8);
        this.setMaxCooldown(10);
        this.setExperiencePoint(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(MANA_RESISTANCE);
        player.getAttribute(ModAttributes.FALL_RESISTANCE.get()).addTransientModifier(MANA_RESISTANCE);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).addTransientModifier(MANA_STEP);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(MANA_RESISTANCE);
        player.getAttribute(ModAttributes.FALL_RESISTANCE.get()).removeModifier(MANA_RESISTANCE);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(MANA_STEP);
        return true;
    }
}
