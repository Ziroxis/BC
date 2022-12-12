package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.world.gen.ModBiomeGeneration;
import com.yuanno.block_clover.world.gen.ModEntityGeneration;
import com.yuanno.block_clover.world.gen.ModStructureGeneration;
import com.yuanno.block_clover.world.gen.TreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModEntityGeneration.onEntitySpawn(event);
        ModBiomeGeneration.generateBiomes();
        ModStructureGeneration.generateStructures(event);
        TreeGeneration.generateTrees(event);
        /*
        ModEntityGeneration.onEntitySpawn(event);
        ModTreeGeneration.generateTrees(event);

         */
    }
}