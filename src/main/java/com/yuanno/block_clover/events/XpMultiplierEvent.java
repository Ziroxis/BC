package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.effects.XpMultiplierEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class XpMultiplierEvent {

    @SubscribeEvent
    public static void xpMultiplierEffectStart(PotionEvent.PotionAddedEvent event)
    {
        if (event.getPotionEffect() != null && event.getPotionEffect().getEffect() instanceof XpMultiplierEffect)
        {
            if (event.getEntity() instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                IEntityStats entityStats = EntityStatsCapability.get(player);
                entityStats.alterMultiplier(0.5f);
            }
        }
    }

    @SubscribeEvent
    public static void xpMultiplierEffectEnd(PotionEvent.PotionExpiryEvent event)
    {
        if (event.getPotionEffect() != null && event.getPotionEffect().getEffect() instanceof XpMultiplierEffect)
        {
            if (event.getEntity() instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                IEntityStats entityStats = EntityStatsCapability.get(player);
                entityStats.alterMultiplier(-0.5f);
            }
        }
    }
}
