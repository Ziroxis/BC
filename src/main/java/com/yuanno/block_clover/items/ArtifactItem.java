package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public abstract class ArtifactItem extends Item {

    public ArtifactItem()
    {
        super(new Properties().durability(500).tab(ModItemGroup.BLOCK_CLOVER_ARTIFACTS));
    }


    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }
}
