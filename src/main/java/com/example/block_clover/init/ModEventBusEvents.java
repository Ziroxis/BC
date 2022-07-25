package com.example.block_clover.init;

import com.example.block_clover.entities.GrimoireMagicianEntity;
import com.example.block_clover.entities.summons.earth.EarthGolemEntity;
import com.example.block_clover.entities.summons.earth.EarthMinionEntity;
import com.example.block_clover.entities.summons.earth.EarthSummons;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        //CURSED SPIRITS
        event.put(EarthSummons.EARTH_MINION.get(), EarthMinionEntity.setCustomAttributes().build());
        event.put(EarthSummons.EARTH_GOLEM.get(), EarthGolemEntity.setCustomAttributes().build());
        event.put(ModEntities.GRIMOIRE_MAGICIAN.get(), GrimoireMagicianEntity.setCustomAttributes().build());
    }
}