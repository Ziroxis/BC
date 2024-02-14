package com.yuanno.block_clover.events.levelEvents;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenTeleportTowerScreenPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class LevelUpEvents {

    @SubscribeEvent
    public static void levelUp(LevelUpEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterLevel(1);
        entityStats.setMaxExperience(100 * entityStats.getLevel()/2);
        entityStats.setExperience(0);
        if (entityStats.getRace().equals(ModValues.ELF)) {
            entityStats.alterMaxMana(100);
            entityStats.alterManaRegeneration(0.1f);
        }
        else {
            entityStats.alterMaxMana(50);
            entityStats.alterManaRegeneration(0.05f);
        }
        if (entityStats.getLevel() == 5)
            PacketHandler.sendTo(new SOpenTeleportTowerScreenPacket(), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
    }
}
