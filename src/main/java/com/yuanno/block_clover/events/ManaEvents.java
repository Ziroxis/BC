package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
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
        float regen = propsEntity.getManaRegeneration();





        if (player.tickCount % 20 == 0 && propsEntity.getMana() < propsEntity.getMaxMana())
        {
            propsEntity.alterMana(regen);
            if (propsEntity.getAttribute().equals(ModValues.TIME) || propsEntity.getSecondAttribute().equals(ModValues.TIME) && propsEntity.getTime() < 1000)
                propsEntity.alterTime(1);
        }
    }
}
