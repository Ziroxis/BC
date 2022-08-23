package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ValkyrieArmorAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ValkyrieArmorAbility INSTANCE = new ValkyrieArmorAbility();

    public static final AttributeModifier WATER_ATTACK = new AttributeModifier(UUID.fromString("43beae28-231f-11ed-861d-0242ac120002"),
            "Water Attack", 3, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier WATER_DEFENSE = new AttributeModifier(UUID.fromString("5a476dd8-231f-11ed-861d-0242ac120002"),
            "Water Defense", 2, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier WATER_RANGE = new AttributeModifier(UUID.fromString("c9b016d4-231f-11ed-861d-0242ac120002"),
            "Water Defense", 2, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier WATER_SPEED = new AttributeModifier(UUID.fromString("697c7d48-231f-11ed-861d-0242ac120002"),
            "Water Speed", 1, AttributeModifier.Operation.ADDITION);

    public ValkyrieArmorAbility()
    {
        super("Valkyrie Armor", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Envelops yourself with an armor of water");
        this.setMaxCooldown(120);
        this.setmanaCost(10);
        this.setExperiencePoint(20);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(WATER_ATTACK);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(WATER_RANGE);
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(WATER_SPEED);

        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(WATER_ATTACK);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(WATER_RANGE);
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(WATER_SPEED);

        return true;
    }
}
