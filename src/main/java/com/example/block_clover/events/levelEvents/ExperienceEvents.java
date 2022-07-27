package com.example.block_clover.events.levelEvents;

import com.example.block_clover.Main;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModValues;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ExperienceEvents {

    @SubscribeEvent
    public static void onLevelUp(ExperienceUpEvent e)
    {
        PlayerEntity player = e.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);

        if (statsProps.getExperience() >= statsProps.getMaxExperience())
        {
            int currentExperience = statsProps.getExperience();
            int currentMaxExperience = statsProps.getMaxExperience();

            statsProps.alterLevel(1);
            LevelUpEvent eventLevelUp = new LevelUpEvent(player, statsProps.getLevel());
            if (MinecraftForge.EVENT_BUS.post(eventLevelUp))
                return;
            statsProps.alterMaxExperience(100);
            MaxExperienceUpEvent eventMaxExperienceUp = new MaxExperienceUpEvent(player, statsProps.getMaxExperience());
            if (MinecraftForge.EVENT_BUS.post(eventMaxExperienceUp))
                return;
            statsProps.setExperience(currentExperience - currentMaxExperience);
            ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, statsProps.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                return;
            statsProps.alterMaxMana(10);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
            //player.sendMessage(new StringTextComponent("You leveled up to level " + statsProps.getLevel() + "!"), player.getUUID());
        }
    }
}