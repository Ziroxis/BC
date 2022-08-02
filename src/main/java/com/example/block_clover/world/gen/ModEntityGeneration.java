package com.example.block_clover.world.gen;

import com.example.block_clover.init.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;

public class ModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);

        if (!types.contains(BiomeDictionary.Type.WET) && !types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.RIVER))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BANDIT.get(), 50, 2, 4));
        }
    }
}
