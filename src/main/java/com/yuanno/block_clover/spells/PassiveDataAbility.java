package com.yuanno.block_clover.spells;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.ability.AbilityDataBase;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;

public class PassiveDataAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Passive Data", AbilityCategories.AbilityCategory.ATTRIBUTE, PassiveDataAbility.class)
            .setDescription("")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private float experience = 0;
    public PassiveDataAbility()
    {
        super(INSTANCE);
        //this.hideInGUI(true);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void duringPassiveEvent(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.getUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
    }

}
