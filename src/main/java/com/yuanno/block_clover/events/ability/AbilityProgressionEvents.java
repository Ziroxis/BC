package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.darkness.AvidyaSlashAbility;
import com.yuanno.block_clover.spells.darkness.AvidyaWildSlashAbility;
import com.yuanno.block_clover.spells.darkness.BlackHoleAbility;
import com.yuanno.block_clover.spells.darkness.BluntStrikeAbility;
import com.yuanno.block_clover.spells.earth.*;
import com.yuanno.block_clover.spells.fire.*;
import com.yuanno.block_clover.spells.light.LightBladeShowerAbility;
import com.yuanno.block_clover.spells.light.LightHealingAbility;
import com.yuanno.block_clover.spells.light.LightMovementAbility;
import com.yuanno.block_clover.spells.light.LightSwordAbility;
import com.yuanno.block_clover.spells.wind.ToweringTornadoAbility;
import com.yuanno.block_clover.spells.wind.WindBladeShowerAbility;
import com.yuanno.block_clover.spells.wind.WindCrescentAbility;
import com.yuanno.block_clover.spells.wind.WindFlightAbility;
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.slash.*;
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

        if (!statsProps.hasGrimoire())
            return;
        if (statsProps.getAttribute().equals(ModValues.WIND) || statsProps.getSecondAttribute().equals(ModValues.WIND))
        {
            gainAbility(event.getPlayer(), 3, WindCrescentAbility.INSTANCE);
            gainAbility(event.getPlayer(), 7, WindBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ToweringTornadoAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, WindFlightAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.LIGHT) || statsProps.getSecondAttribute().equals(ModValues.LIGHT))
        {
            gainAbility(event.getPlayer(), 5, LightSwordAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, LightBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, LightHealingAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LightMovementAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.LIGHTNING) || statsProps.getSecondAttribute().equals(ModValues.LIGHTNING))
        {
            gainAbility(event.getPlayer(), 3, ThunderFiendAbility.INSTANCE);
            gainAbility(event.getPlayer(), 5, ThunderGodGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, ThunderCrumblingOrbAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ThunderSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, ThunderFiendMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, ThunderCrumblingOrbMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 30, ThunderChargeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 35, ThunderDischargeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.FIRE) || statsProps.getSecondAttribute().equals(ModValues.FIRE))
        {
            gainAbility(event.getPlayer(), 5, FlameRoarAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, SpiralFlameAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, SolLineaAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LeoPalmaAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, WildBurstingFlamesAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.EARTH) || statsProps.getSecondAttribute().equals(ModValues.EARTH))
        {
            gainAbility(event.getPlayer(), 3, EarthPassiveAbility.INSTANCE);
            gainAbility(event.getPlayer(), 5, EarthChargeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, EarthGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, EarthMinionAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, EarthGolemAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, EarthQuakeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.DARKNESS) || statsProps.getSecondAttribute().equals(ModValues.DARKNESS))
        {
            gainAbility(event.getPlayer(), 3, AvidyaSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, BluntStrikeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, AvidyaWildSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, BlackHoleAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.SLASH) || statsProps.getSecondAttribute().equals(ModValues.SLASH))
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
