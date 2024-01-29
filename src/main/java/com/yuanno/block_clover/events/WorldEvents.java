package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.world.spawners.AmbushSpawner;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class WorldEvents {

    private static final AmbushSpawner AMBUSH_SPAWNER = new AmbushSpawner();

    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && event.world.dimension() == World.OVERWORLD)
        {
            if (!event.world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
                return;

            event.world.getProfiler().push("worldSpawners");

            AMBUSH_SPAWNER.tick((ServerWorld) event.world);
            event.world.getProfiler().pop();
        }

        if (event.phase == TickEvent.Phase.END)
        {
            event.world.getProfiler().push("worldEvents");
            //MANA_SIPHONING.tick((ServerWorld) event.world);
            event.world.getProfiler().pop();
        }
    }
}
