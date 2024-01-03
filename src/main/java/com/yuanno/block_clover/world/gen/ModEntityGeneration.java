package com.yuanno.block_clover.world.gen;

import com.yuanno.block_clover.init.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.lwjgl.system.CallbackI;

import java.util.Set;

public class ModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);

        if (!types.contains(BiomeDictionary.Type.WET) && !types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.RIVER)  && !types.contains(BiomeDictionary.Type.WATER))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BANDIT.get(), 20, 2, 4));
        }
        if (types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.DEAD) || types.contains(BiomeDictionary.Type.MESA))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.VOLCANO_MONSTER.get(), 10, 1, 2));
        }
        if (types.contains(BiomeDictionary.Type.JUNGLE) || types.contains(BiomeDictionary.Type.FOREST))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.MONKEY_ENTITY.get(), 15, 2, 4));
        }
        if (types.contains(BiomeDictionary.Type.OCEAN) || types.contains(BiomeDictionary.Type.BEACH))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CLOVER_SHARK.get(), 1, 1, 2));
        }
    }
}
