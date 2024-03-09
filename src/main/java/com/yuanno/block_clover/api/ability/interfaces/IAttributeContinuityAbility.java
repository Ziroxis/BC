package com.yuanno.block_clover.api.ability.interfaces;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;

public interface IAttributeContinuityAbility {

    default void applyAttributes(PlayerEntity player, Ability ability) {
        Map<Attribute, AttributeModifier> attributeMap = getAttributeMap();

        for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
            Attribute attribute = entry.getKey();
            AttributeModifier attributeModifier = entry.getValue();

            // Apply the modifier to the player's attribute
            player.getAttribute(attribute).addTransientModifier(attributeModifier);
        }
    }

    default void removeAttributes(PlayerEntity player, Ability ability) {
        Map<Attribute, AttributeModifier> attributeMap = getAttributeMap();

        for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
            Attribute attribute = entry.getKey();
            AttributeModifier attributeModifier = entry.getValue();

            // Apply the modifier to the player's attribute
            player.getAttribute(attribute).removeModifier(attributeModifier);
        }
    }


    default Map<Attribute, AttributeModifier> getAttributeMap() {
        Map<Attribute, AttributeModifier> attributeMap = new HashMap<>();
        return attributeMap;
    }
}
