package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import net.minecraft.entity.player.PlayerEntity;


public class BeastRegenerationPassiveAbility extends PassiveAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Ferral Regeneration", AbilityCategories.AbilityCategory.ATTRIBUTE, BeastRegenerationPassiveAbility.class)
            .setDescription("You unleash your inner beast, granting you regeneration")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    //private static final AttributeModifier FERRAL_REGENERATION = new AttributeModifier(UUID.fromString("9cc104e4-d215-11ed-afa1-0242ac120002"),
      //      "Rhinoceros Resistance", 2, AttributeModifier.Operation.ADDITION);

    public BeastRegenerationPassiveAbility()
    {
        super(INSTANCE);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void duringPassiveEvent(PlayerEntity player)
    {
        if (player.tickCount % 20 == 0 && player.getHealth() < player.getMaxHealth())
        {
            if (player.getHealth() > 10)
                player.heal(0.5f);
            else if (player.getHealth() < 10)
                player.heal(1f);
        }
    }

}
