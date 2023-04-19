package com.yuanno.block_clover.items.food;

import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class RegenerativeFood extends Item {
    public RegenerativeFood(float saturationmod, int nutrition, int durationinsec) {
        super(new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_FOOD).food(new Food.Builder().saturationMod(saturationmod)
                .nutrition(nutrition).effect(() -> {
                    Supplier<EffectInstance> sup;
                        return new EffectInstance(ModEffects.MANA_REGENERATION.get(), durationinsec*20, 0);
                }, 1).effect(new EffectInstance(Effects.REGENERATION,40,0),1).build()));
    }
}
