package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.world.ChallengesWorldData;
import com.yuanno.block_clover.world.gen.ModBiomeGeneration;
import com.yuanno.block_clover.world.gen.ModEntityGeneration;
import com.yuanno.block_clover.world.gen.ModStructureGeneration;
import com.yuanno.block_clover.world.gen.TreeGeneration;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
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

    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.world.dimension().location().toString().contains("challenges_")) {
                event.world.getProfiler().push("challengesManager");

                ChallengesWorldData.get().tick((ServerWorld) event.world);

                event.world.getProfiler().pop();
            }
        }
    }
}