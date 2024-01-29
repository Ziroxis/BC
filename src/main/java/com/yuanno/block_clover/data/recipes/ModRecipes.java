package com.yuanno.block_clover.data.recipes;

import com.yuanno.block_clover.Main;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ModRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);

    public static final RegistryObject<JuicerRecipe.Serializer> JUICER_SERIALIZER = RECIPE_SERIALIZER.register("juicer", JuicerRecipe.Serializer::new);
    public static final RegistryObject<BakingOvenRecipe.Serializer> BAKING_OVEN_SERIALIZER = RECIPE_SERIALIZER.register("baking_oven", BakingOvenRecipe.Serializer::new);

    public static final IRecipeType<JuicerRecipe> JUICER_RECIPE = new JuicerRecipe.JuicerRecipeType();
    public static final IRecipeType<BakingOvenRecipe> BAKING_OVEN_RECIPE = new BakingOvenRecipe.BakingOvenRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, JuicerRecipe.ID, JUICER_RECIPE);
    }
}
