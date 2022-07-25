package com.example.block_clover.init;

import com.example.block_clover.Main;
import com.example.block_clover.world.gen.ModStructureGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModStructureGeneration.generateStructures(event);
        /*
        ModEntityGeneration.onEntitySpawn(event);
        ModTreeGeneration.generateTrees(event);

         */
    }
}