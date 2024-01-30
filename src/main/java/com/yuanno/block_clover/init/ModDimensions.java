package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.world.ChallengesBiomeProvider;
import com.yuanno.block_clover.world.ChallengesChunkGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModDimensions {

    public static void setupDimensions() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Main.MODID, "challenges_biome_provider"), ChallengesBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Main.MODID, "challenges_chunk_generator"), ChallengesChunkGenerator.CODEC);
    }
}
