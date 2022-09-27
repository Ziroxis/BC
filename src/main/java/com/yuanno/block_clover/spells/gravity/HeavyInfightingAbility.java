package com.yuanno.block_clover.spells.gravity;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class HeavyInfightingAbility extends ContinuousAbility {

    public static final HeavyInfightingAbility INSTANCE = new HeavyInfightingAbility();
    private static final AttributeModifier HEAVY_INFIGHTING = new AttributeModifier(UUID.fromString("4f525e0e-3e92-11ed-b878-0242ac120002"),
            "Heavy Infighting", 5, AttributeModifier.Operation.ADDITION);

    public HeavyInfightingAbility()
    {
        super("Heavy infighting", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Lowers the weight of your punches, giving attack speed.\nAugmenting the weight of your punches when hitting something, giving damage");
        this.setMaxCooldown(0);
        this.setmanaCost(0);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(HEAVY_INFIGHTING);
        player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(HEAVY_INFIGHTING);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(HEAVY_INFIGHTING);
        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(HEAVY_INFIGHTING);
        return true;
    }
}
