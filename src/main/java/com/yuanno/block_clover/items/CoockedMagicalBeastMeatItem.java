package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CoockedMagicalBeastMeatItem extends Item {
    public CoockedMagicalBeastMeatItem()
    {
        super(new Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)
                .food(new Food.Builder()
                        .nutrition(8)
                        .saturationMod(1.2f)
                        .effect(new EffectInstance(Effects.REGENERATION, 40, 0), 0.5f)
                        .meat()
                        .build()));
    }
}
