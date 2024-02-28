package com.yuanno.block_clover.events.levelEvents;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.helper.GainSpellHelper;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenSpellChoiceScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenTeleportTowerScreenPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class LevelUpEvents {
    static ArrayList<Integer> levelsWater = new ArrayList<>();
    static {
        levelsWater.add(5);
        levelsWater.add(10);
        levelsWater.add(15);
        levelsWater.add(20);
        levelsWater.add(25);
        levelsWater.add(30);
        levelsWater.add(35);
        levelsWater.add(40);
        levelsWater.add(45);
        levelsWater.add(50);
    }

    @SubscribeEvent
    public static void levelUp(LevelUpEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (player.level.isClientSide)
            return;
        standardLevelUp(player);
        towerLevelUp(player);
        if (entityStats.hasGrimoire())
            spellLevelUp(player);
    }

    private static void standardLevelUp(PlayerEntity player)
    {
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
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
    }
    private static void towerLevelUp(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getLevel() == 5)
            PacketHandler.sendTo(new SOpenTeleportTowerScreenPacket(), player);
    }
    private static void spellLevelUp(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String attribute = entityStats.getAttribute();
        ArrayList<Ability> abilities = GainSpellHelper.returnAbilitiesInPool(attribute, entityStats.getLevel(), player);


        if (attribute.equals(ModValues.WATER) && !levelsWater.contains(entityStats.getLevel()))
            return;
        Collections.shuffle(abilities);
        ArrayList<Ability> randomThree = new ArrayList<>();
        if (!abilities.isEmpty())
            randomThree = new ArrayList<>(abilities.subList(0, 3));
        if (!randomThree.isEmpty())
            PacketHandler.sendTo(new SOpenSpellChoiceScreenPacket(randomThree), player);
    }


}
