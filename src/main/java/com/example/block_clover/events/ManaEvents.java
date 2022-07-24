package com.example.block_clover.events;

import com.example.block_clover.Main;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaEvents {

    @SubscribeEvent
    public static void regenerateMana(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        float regen = 1 + (float) propsEntity.getLevel()/10;

        if (player.tickCount % 20 == 0 && propsEntity.getMana() < propsEntity.getMaxMana())
        {
            propsEntity.alterMana(regen);
            System.out.println(propsEntity.getMana());
        }
    }
}
