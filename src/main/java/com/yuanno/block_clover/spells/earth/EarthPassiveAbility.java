package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;

public class EarthPassiveAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Earth passive", AbilityCategories.AbilityCategory.ATTRIBUTE, EarthPassiveAbility.class)
            .setDescription("Every time you fall, you do damage around you depending on how high you fall. \nIf you fall on dirt, stone or cobble. Your fall damage is negated")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();

    public EarthPassiveAbility()
    {
        super(INSTANCE);
        this.setDescription("Every time you fall, you do damage around you depending on how high you fall. \nIf you fall on dirt, stone or cobble. Your fall damage is negated.");
        //this.hideInGUI(false);

    }


}
