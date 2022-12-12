package com.yuanno.block_clover.world.gen;

import com.yuanno.block_clover.world.biome.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomeGeneration {

    public static void generateBiomes() {
        addBiome(ModBiomes.GRAND_MAGIC_ZONE_VOLCANO.get(), BiomeManager.BiomeType.WARM, (int) 1, HOT, DEAD, DRY);
        addBiome(ModBiomes.MOGURO_FOREST.get(), BiomeManager.BiomeType.COOL, 1, FOREST, FOREST, FOREST);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
        RegistryKey<Biome> key = RegistryKey.create(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }
}
