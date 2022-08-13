package com.yuanno.block_clover.init;

import com.yuanno.block_clover.entities.BanditEntity;
import com.yuanno.block_clover.entities.GrimoireMagicianEntity;
import com.yuanno.block_clover.entities.MonkeyEntity;
import com.yuanno.block_clover.entities.VolcanoMonsterEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthGolemEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthSummons;
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
        event.put(ModEntities.BANDIT.get(), BanditEntity.setCustomAttributes().build());
        event.put(ModEntities.VOLCANO_MONSTER.get(), VolcanoMonsterEntity.setCustomAttributes().build());
        event.put(ModEntities.MONKEY_ENTITY.get(), MonkeyEntity.setCustomAttributes().build());
    }
}