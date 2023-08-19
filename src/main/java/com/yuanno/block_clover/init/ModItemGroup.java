package com.yuanno.block_clover.init;

import com.yuanno.block_clover.curios.CuriosRegistry;
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

    public static final ItemGroup BLOCK_CLOVER_ARTIFACTS = new ItemGroup("block_cloverModTabArtifacts") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RABBIT_FOOT.get());
        }
    };
    public static final ItemGroup BLOCK_CLOVER_MISC = new ItemGroup("block_cloverMisc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MAGICAL_MEAT.get());
        }
    };
    public static final ItemGroup BLOCK_CLOVER_BLOCKS = new ItemGroup("block_cloverBlocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ELDER_SAPLING.get());
        }
    };


    public static final ItemGroup BLOCK_CLOVER_FOOD = new ItemGroup("block_cloverModTabFood") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MOGURO_JUICE.get());
        }
    };


}
