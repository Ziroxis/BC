package com.example.block_clover.events;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModValues;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncAbilityDataPacket;
import com.example.block_clover.networking.server.SSyncEntityStatsPacket;
import com.example.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.example.block_clover.spells.earth.EarthChargeAbility;
import com.example.block_clover.spells.fire.FireBallAbility;
import com.example.block_clover.spells.light.LightBladeAbility;
import com.example.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.example.block_clover.spells.slash.SlashBladesAbility;
import com.example.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsEvent {

    @SubscribeEvent
    public static void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (!props.hasAttribute())
        {
            props.setLevel(1);
            props.setExperience(0);
            props.setMaxExperience(100);
            props.setMana(50);
            props.setMaxMana(50);
            props.setAttribute(Beapi.randomizer(ModValues.attributes));
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
            String attribute = props.getAttribute();
            if (attribute.equals(ModValues.WIND))
                abilityProps.addUnlockedAbility(WindBladeAbility.INSTANCE);
            if (attribute.equals(ModValues.FIRE))
                abilityProps.addUnlockedAbility(FireBallAbility.INSTANCE);
            if (attribute.equals(ModValues.LIGHT))
                abilityProps.addUnlockedAbility(LightBladeAbility.INSTANCE);
            if (attribute.equals(ModValues.LIGHTNING))
                abilityProps.addUnlockedAbility(ThunderGodBootsAbility.INSTANCE);
            if (attribute.equals(ModValues.DARKNESS))
                abilityProps.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
            if (attribute.equals(ModValues.EARTH))
                abilityProps.addUnlockedAbility(EarthChargeAbility.INSTANCE);
            if (attribute.equals(ModValues.SLASH))
                abilityProps.addUnlockedAbility(SlashBladesAbility.INSTANCE);
        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);

    }
}
