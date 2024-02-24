package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;

public class CurrentOfTheFortuneRiverAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Current Of The Fortune River", AbilityCategories.AbilityCategory.ATTRIBUTE, CurrentOfTheFortuneRiverAbility.class)
            .setDescription("Allows you to walk on water")
            .build();

    public CurrentOfTheFortuneRiverAbility()
    {
        super(INSTANCE);
    }
}
