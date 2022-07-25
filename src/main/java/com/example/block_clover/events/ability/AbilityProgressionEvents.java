package com.example.block_clover.events.ability;

import com.example.block_clover.Main;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.events.levelEvents.ExperienceUpEvent;
import com.example.block_clover.init.ModValues;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncAbilityDataPacket;
import com.example.block_clover.spells.darkness.AvidyaSlashAbility;
import com.example.block_clover.spells.earth.*;
import com.example.block_clover.spells.fire.FlameRoarAbility;
import com.example.block_clover.spells.fire.LeoPalmaAbility;
import com.example.block_clover.spells.fire.SolLineaAbility;
import com.example.block_clover.spells.fire.SpiralFlameAbility;
import com.example.block_clover.spells.light.LightBladeShowerAbility;
import com.example.block_clover.spells.light.LightHealingAbility;
import com.example.block_clover.spells.light.LightMovementAbility;
import com.example.block_clover.spells.light.LightSwordAbility;
import com.example.block_clover.spells.lightning.*;
import com.example.block_clover.spells.slash.*;
import com.example.block_clover.spells.wind.ToweringTornadoAbility;
import com.example.block_clover.spells.wind.WindBladeShowerAbility;
import com.example.block_clover.spells.wind.WindCrescentAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProgressionEvents {

    @SubscribeEvent
    public static void onLevelGained(ExperienceUpEvent event)
    {
        IEntityStats statsProps = EntityStatsCapability.get(event.getPlayer());

        if (statsProps.getAttribute().equals(ModValues.WIND))
        {
            gainAbility(event.getPlayer(), 3, WindCrescentAbility.INSTANCE);
            gainAbility(event.getPlayer(), 7, WindBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ToweringTornadoAbility.INSTANCE);

        }
        if (statsProps.getAttribute().equals(ModValues.LIGHT))
        {
            gainAbility(event.getPlayer(), 5, LightSwordAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, LightBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, LightHealingAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LightMovementAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.LIGHTNING))
        {
            gainAbility(event.getPlayer(), 3, ThunderFiendAbility.INSTANCE);
            gainAbility(event.getPlayer(), 5, ThunderGodGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, ThunderCrumblingOrbAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ThunderFiendMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, ThunderCrumblingOrbMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, ThunderDischargeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.FIRE))
        {
            gainAbility(event.getPlayer(), 5, FlameRoarAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, SpiralFlameAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, SolLineaAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LeoPalmaAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.EARTH))
        {
            gainAbility(event.getPlayer(), 3, EarthPassiveAbility.INSTANCE);
            gainAbility(event.getPlayer(), 5, EarthChargeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, EarthGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, EarthMinionAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, EarthGolemAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, EarthQuakeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.DARKNESS))
        {
            gainAbility(event.getPlayer(), 3, AvidyaSlashAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.SLASH))
        {
            gainAbility(event.getPlayer(), 3, DeathScytheAbility.INSTANCE);
            gainAbility(event.getPlayer(), 5, DeathScytheShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, ForwardThrustAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, LunaticSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, RoundLunaticSlashAbility.INSTANCE);
        }
    }

    private static void gainAbility(PlayerEntity player, int level, Ability ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            player.sendMessage(new StringTextComponent("You unlocked a new spell!."), player.getUUID());
            abilityProps.addUnlockedAbility(ability);
        }
        if ((props.getLevel() < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
