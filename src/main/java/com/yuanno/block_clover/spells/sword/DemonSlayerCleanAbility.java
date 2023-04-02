package com.yuanno.block_clover.spells.sword;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ItemAbility;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DemonSlayerCleanAbility extends ItemAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Original Demon Slayer", AbilityCategories.AbilityCategory.ATTRIBUTE, DemonSlayerCleanAbility.class)
            .setDescription("Takes the demon slayer sword out of your grimoire")
            .setDamageKind(AbilityDamageKind.ITEM)
            .build();

    public DemonSlayerCleanAbility()
    {
        super(INSTANCE);

        this.setMaxCooldown(0);
        this.setmanaCost(0);
    }

    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        return new ItemStack(ModItems.DEMON_DWELLER.get());
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }
}
