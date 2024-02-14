package com.yuanno.block_clover.events.levelEvents;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ExperienceEvents {

    @SubscribeEvent
    public static void onLevelUp(ExperienceUpEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats statsProps = EntityStatsCapability.get(player);

        if (statsProps.getExperience() >= statsProps.getMaxExperience() && statsProps.getLevel() < 50)
        {
            LevelUpEvent eventLevelUp = new LevelUpEvent(player, statsProps.getLevel());
            MinecraftForge.EVENT_BUS.post(eventLevelUp);
        }
    }
}
