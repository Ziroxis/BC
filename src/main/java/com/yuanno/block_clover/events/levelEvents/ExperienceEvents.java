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
    public static void onLevelUp(ExperienceUpEvent e)
    {
        PlayerEntity player = e.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);

        if (statsProps.getExperience() >= statsProps.getMaxExperience() && statsProps.getLevel() < 50)
        {
            int currentExperience = statsProps.getExperience();
            int currentMaxExperience = statsProps.getMaxExperience();
            statsProps.alterLevel(1);
            LevelUpEvent eventLevelUp = new LevelUpEvent(player, statsProps.getLevel());
            if (MinecraftForge.EVENT_BUS.post(eventLevelUp))
                return;
            statsProps.alterMaxExperience(100 * statsProps.getLevel()/2);
            MaxExperienceUpEvent eventMaxExperienceUp = new MaxExperienceUpEvent(player, statsProps.getMaxExperience());
            if (MinecraftForge.EVENT_BUS.post(eventMaxExperienceUp))
                return;
            statsProps.setExperience(currentExperience - currentMaxExperience);
            ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, statsProps.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                return;
            if (statsProps.getRace().equals(ModValues.ELF)) {
                statsProps.alterMaxMana(100);
                statsProps.alterManaRegeneration(0.1f);
            }
            else {
                statsProps.alterMaxMana(50);
                statsProps.alterManaRegeneration(0.05f);
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
            //player.sendMessage(new StringTextComponent("You leveled up to level " + statsProps.getLevel() + "!"), player.getUUID());
        }
    }
}
