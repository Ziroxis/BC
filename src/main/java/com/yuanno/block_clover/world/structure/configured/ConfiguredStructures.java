package com.yuanno.block_clover.world.structure.configured;

import com.yuanno.block_clover.AttachingCapabilities;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.init.ModStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ConfiguredStructures
{
    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_MAGICTOWER = ModStructures.MAGICTOWER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BANDIT_CAMP = ModStructures.BANDIT_CAMP.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_MINI_VOLCANO = ModStructures.MINI_VOLCANO.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_UNDERWATER_DUNGEON = ModStructures.UNDERWATER_DUNGEON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_FIRE_DUNGEON = ModStructures.FIRE_DUNGEON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_GUILD_TOWER = ModStructures.GUILD_TOWER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_RUINS = ModStructures.RUINS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_DEVIL_CHURCH = ModStructures.DEVIL_CHURCH.get().configured(IFeatureConfig.NONE);





    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(Main.MODID, "magictower"), CONFIGURED_MAGICTOWER);
        Registry.register(registry, new ResourceLocation(Main.MODID, "bandit_camp"), CONFIGURED_BANDIT_CAMP);
        Registry.register(registry, new ResourceLocation(Main.MODID, "mini_volcano"), CONFIGURED_MINI_VOLCANO);
        Registry.register(registry, new ResourceLocation(Main.MODID, "underwater_dungeon"), CONFIGURED_UNDERWATER_DUNGEON);
        Registry.register(registry, new ResourceLocation(Main.MODID, "fire_dungeon"), CONFIGURED_FIRE_DUNGEON);
        Registry.register(registry, new ResourceLocation(Main.MODID, "guild_tower"), CONFIGURED_GUILD_TOWER);
        Registry.register(registry, new ResourceLocation(Main.MODID, "ruins"), CONFIGURED_RUINS);
        Registry.register(registry, new ResourceLocation(Main.MODID, "devil_church"), CONFIGURED_DEVIL_CHURCH);


        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
         * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
         * FlatGenerationSettings.STRUCTURE_FEATURES in it and you don't add your structure to it, the game
         * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
         *
         * (It would also crash with superflat worldtype if you omit the below line
         * and attempt to add the structure's StructureSeparationSettings to the world)
         *
         * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
         * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
         * and re-enter it and your structures will be spawning. I could not figure out why it needs
         * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
         *
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.MAGICTOWER.get(), CONFIGURED_MAGICTOWER);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.BANDIT_CAMP.get(), CONFIGURED_BANDIT_CAMP);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.MINI_VOLCANO.get(), CONFIGURED_MINI_VOLCANO);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.UNDERWATER_DUNGEON.get(), CONFIGURED_UNDERWATER_DUNGEON);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.FIRE_DUNGEON.get(), CONFIGURED_FIRE_DUNGEON);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.GUILD_TOWER.get(), CONFIGURED_GUILD_TOWER);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.RUINS.get(), CONFIGURED_RUINS);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.DEVIL_CHURCH.get(), CONFIGURED_DEVIL_CHURCH);

    }
}
