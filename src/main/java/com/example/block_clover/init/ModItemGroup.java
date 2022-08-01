package com.example.block_clover.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup BLOCK_CLOVER_WEAPONS = new ItemGroup("block_cloverModTabWeapons")
    {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DEMON_SLAYER.get());
        }
    };
    public static final ItemGroup BLOCK_CLOVER_CLOTHES = new ItemGroup("block_cloverModTabClothes") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ASTA_HAT2.get());
        }
    };
}