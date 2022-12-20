package com.yuanno.block_clover.world.gen;

import com.yuanno.block_clover.init.ModConfiguredFeatures;
import com.yuanno.block_clover.world.biome.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class TreeGeneration {

    public static void generateTrees(final BiomeLoadingEvent event)
    {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        //what biome it should spawn in
        if (event.getName().equals(ModBiomes.MOGURO_FOREST.get().getRegistryName()))
        {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            //Tre to add
            base.add(() -> ModConfiguredFeatures.MOGURO_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(8, 0.25f, 1))));
        }
    }
}
