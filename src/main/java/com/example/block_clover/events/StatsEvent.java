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
            props.setAttribute(Beapi.randomizer(ModValues.attributes));
            String attribute = props.getAttribute();
            switch (attribute)
            {
                case "Wind":
                    abilityProps.addUnlockedAbility(WindBladeAbility.INSTANCE);
                    break;
                case "Fire":
                    abilityProps.addUnlockedAbility(FireBallAbility.INSTANCE);
                    break;
                case "Light":
                    abilityProps.addUnlockedAbility(LightBladeAbility.INSTANCE);
                    break;
                case "Darkness":
                    abilityProps.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
                    break;
                case "Earth":
                    abilityProps.addUnlockedAbility(EarthChargeAbility.INSTANCE);
                case "Slash":
                    abilityProps.addUnlockedAbility(SlashBladesAbility.INSTANCE);
                    break;
            }
            props.setLevel(1);
            props.setExperience(0);
            props.setMaxExperience(100);
            if (!attribute.equals(ModValues.ANTIMAGIC))
            {
                props.setMana(50);
                props.setMaxMana(50);
            }
            else
            {
                props.setMana(0);
                props.setMaxMana(0);
            }
        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);

    }
}
