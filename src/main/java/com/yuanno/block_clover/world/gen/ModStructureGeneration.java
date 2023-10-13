package com.yuanno.block_clover.world.gen;

import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.world.biome.ModBiomes;
import com.yuanno.block_clover.world.structure.configured.ConfiguredStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;


public class ModStructureGeneration
{
    //structures that spawn in specific biomes
    public static void generateStructures(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);

        if (types.contains(BiomeDictionary.Type.PLAINS) && !types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END))
        {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_MAGICTOWER);
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_RUINS);
        }
        if (!types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.DEAD) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.HOT) && !types.contains(BiomeDictionary.Type.WET) && !types.contains(BiomeDictionary.Type.WATER) && !types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER)  )
        {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_BANDIT_CAMP);
        }
        if (types.contains(BiomeDictionary.Type.OCEAN))
        {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_UNDERWATER_DUNGEON);
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_RUINS);
        }
        if (event.getName().equals(ModBiomes.GRAND_MAGIC_ZONE_VOLCANO.get().getRegistryName()))
        {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_FIRE_DUNGEON);
        }
        if (event.getName().equals(ModBiomes.MOGURO_FOREST.get().getRegistryName()))
        {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_GUILD_TOWER);
        }
        if (types.contains(BiomeDictionary.Type.MOUNTAIN) &!types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END))
        {
            //event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_DEVIL_CHURCH);
        }


    }
}
