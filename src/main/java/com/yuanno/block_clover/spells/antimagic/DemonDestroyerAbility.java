package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ItemAbility;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DemonDestroyerAbility extends ItemAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Demon Destroyer", AbilityCategories.AbilityCategory.ATTRIBUTE, DemonDestroyerAbility.class)
            .setDescription("Takes the demon destroyer out of your grimoire")
            .setDamageKind(AbilityDamageKind.ITEM)
            .build();

    public DemonDestroyerAbility()
    {
        super(INSTANCE);

        this.setMaxCooldown(0);
        this.setmanaCost(0);
    }

    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        return new ItemStack(ModItems.DEMON_DESTROYER.get());
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }
}
