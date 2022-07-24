package com.example.block_clover.spells.earth;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.PassiveAbility;
import net.minecraft.entity.player.PlayerEntity;

public class EarthPassiveAbility extends PassiveAbility {
    public static final EarthPassiveAbility INSTANCE = new EarthPassiveAbility();

    public EarthPassiveAbility()
    {
        super("Earth Shake", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Every time you fall, you do damage around you depending on how high you fall. \nIf you fall on dirt, stone or cobble. Your fall damage is negated.");
        this.hideInGUI(false);

    }


}
