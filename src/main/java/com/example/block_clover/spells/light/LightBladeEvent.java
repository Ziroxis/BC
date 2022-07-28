package com.example.block_clover.spells.light;

import com.example.block_clover.Main;
import com.example.block_clover.init.ModItems;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LightBladeEvent {
    @SubscribeEvent
    public static void onLightBladeEvent(ItemTossEvent event)
    {
        if (event.getEntityItem().getItem().getItem().equals(ModItems.LIGHT_SWORD.get()))
            event.getEntityItem().remove();
    }
}
