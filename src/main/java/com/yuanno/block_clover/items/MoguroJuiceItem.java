package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class MoguroJuiceItem extends Item {


    public MoguroJuiceItem() {
        super(new Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC).stacksTo(1)
                .food(new Food.Builder()
                        .nutrition(8)
                        .saturationMod(1.2f)
                        .effect(() -> {
                            Supplier<EffectInstance> sup; {
                                return new EffectInstance(ModEffects.XP_MULTIPLIER.get(), 6000, 0);
                            }
                        }, 1f)
                        .alwaysEat()
                        .build()));


    }


    @Override
    public SoundEvent getEatingSound() {
        return super.getDrinkingSound();
    }


}

