package com.yuanno.block_clover.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MOGURO_TREE =
            register("moguro_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.MOGURO_LOG.get().defaultBlockState()), // log of the tree
                    new SimpleBlockStateProvider(ModBlocks.MOGURO_LEAF.get().defaultBlockState()), // leaf of the tree
                    new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), // how the tree will look like
                    new StraightTrunkPlacer(8, 4, 4), // no clue
                    new TwoLayerFeature(1, 0, 1))).ignoreVines().build())); // no clue
}
