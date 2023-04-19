package com.yuanno.block_clover.data.recipes.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.recipes.BakingOvenRecipe;
import com.yuanno.block_clover.data.recipes.JuicerRecipe;
import com.yuanno.block_clover.init.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BakingOvenRecipeCategory implements IRecipeCategory<BakingOvenRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Main.MODID, "recipies/baking_oven");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/baking_oven_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progressBar;

    public BakingOvenRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 6, 6, 130, 70);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.JUICER.get()));
        this.progressBar = helper.createDrawable(TEXTURE, 176, 0, 29, 12);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends BakingOvenRecipe> getRecipeClass() {
        return BakingOvenRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.BAKING_OVEN.get().getName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(BakingOvenRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BakingOvenRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 41, 28);
        recipeLayout.getItemStacks().init(1, true, 53, 7);
        recipeLayout.getItemStacks().init(2, true, 53, 49);
        recipeLayout.getItemStacks().init(3, true, 60,58);
        recipeLayout.getItemStacks().init(4, true, 70,68);
        recipeLayout.getItemStacks().init(5, true, 80,78);

        recipeLayout.getItemStacks().init(6, false, 101, 28);

        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(BakingOvenRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
    }
}
