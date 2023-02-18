package com.yuanno.block_clover.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yuanno.block_clover.data.recipes.jei.IJuicerRecipe;
import com.yuanno.block_clover.init.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class JuicerRecipe implements IJuicerRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> input;

    public JuicerRecipe(ResourceLocation id, ItemStack out, NonNullList<Ingredient> in) {
        this.id = id;
        this.output = out;
        this.input = in;
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        //Cheks for correct items
        try {
            return input.get(0).test(inv.getItem(0)) && input.get(1).test(inv.getItem(1)) && input.get(2).test(inv.getItem(2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return input;
    }

    @Override
    public ItemStack assemble(IInventory p_77572_1_) {
        return null;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }


    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.JUICER.get());
    }


    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.JUICER_SERIALIZER.get();
    }

    public static class JuicerRecipeType implements IRecipeType<JuicerRecipe> {
        @Override
        public String toString() {
            return JuicerRecipe.ID.toString();
        }
    }


    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<JuicerRecipe> {

        @Override
        public JuicerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new JuicerRecipe(recipeId, output,
                    inputs);
        }

        @Nullable
        @Override
        public JuicerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer)); // reads ingredient
            }

            ItemStack output = buffer.readItem(); // reads stack
            return new JuicerRecipe(recipeId, output,
                    inputs);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, JuicerRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }

}
