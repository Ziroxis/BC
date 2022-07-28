package com.example.block_clover.spells.antimagic;

import com.example.block_clover.Main;
import com.example.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DemonSlayerDropEvent {
    @SubscribeEvent
    public static void onBloodEdgeDrop(ItemTossEvent event)
    {
        if (event.getEntityItem().getItem().getItem().equals(ModItems.DEMON_SLAYER.get()))
        {
            event.getEntityItem().remove();
            PlayerEntity player = event.getPlayer();
        }
    }
}
