package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class MagicalBeastMeatItem extends Item {

    public MagicalBeastMeatItem()
    {
        super(new Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)
                .food(new Food.Builder()
                        .nutrition(6)
                        .saturationMod(0.8f)
                        .meat()
                        .build()));
    }
}
