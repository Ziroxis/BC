package com.yuanno.block_clover.data.recipes.jei;

import com.yuanno.block_clover.Main;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IBakingOvenRecipe extends IRecipe<IInventory> {

    ResourceLocation ID = new ResourceLocation(Main.MODID, "recipes/baking_oven");

    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
