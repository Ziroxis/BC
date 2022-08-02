package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ItemAbility;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DemonSlayerAbility extends ItemAbility implements IParallelContinuousAbility {

    public static final DemonSlayerAbility INSTANCE = new DemonSlayerAbility();

    public DemonSlayerAbility()
    {
        super("Demon Slayer", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Takes the demon dweller sword out of your grimoire");
        this.setMaxCooldown(0);
        this.setmanaCost(0);
    }

    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        return new ItemStack(ModItems.DEMON_SLAYER.get());
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }
}
