package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.LevelUpEvent;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.beast.*;
import com.yuanno.block_clover.spells.darkness.AvidyaWildSlashAbility;
import com.yuanno.block_clover.spells.darkness.BlackHoleAbility;
import com.yuanno.block_clover.spells.darkness.BluntStrikeAbility;
import com.yuanno.block_clover.spells.earth.*;
import com.yuanno.block_clover.spells.fire.*;
import com.yuanno.block_clover.spells.light.LightBladeShowerAbility;
import com.yuanno.block_clover.spells.light.LightHealingAbility;
import com.yuanno.block_clover.spells.light.LightMovementAbility;
import com.yuanno.block_clover.spells.mercury.MercuryBubbleAbility;
import com.yuanno.block_clover.spells.mercury.MercuryRainAbility;
import com.yuanno.block_clover.spells.mercury.MercurySpearAbility;
import com.yuanno.block_clover.spells.sealing.*;
import com.yuanno.block_clover.spells.time.*;
import com.yuanno.block_clover.spells.water.*;
import com.yuanno.block_clover.spells.wind.*;
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.slash.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProgressionEvents {

    @SubscribeEvent
    public static void onLevelGained(LevelUpEvent event)
    {
        IEntityStats statsProps = EntityStatsCapability.get(event.getPlayer());

        if (!statsProps.hasGrimoire())
            return;
        if (statsProps.getAttribute().equals(ModValues.WATER) || statsProps.getSecondAttribute().equals(ModValues.WATER))
        {
            gainAbility(event.getPlayer(), 10, WaterJavelinAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, WaterDragonAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, ValkyrieArmorAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, PointBlankDragonAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.SEALING) || statsProps.getSecondAttribute().equals(ModValues.SEALING))
        {
            gainAbility(event.getPlayer(), 10, SealingPunchAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, SealingWaveAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, OtherHealSealingAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, UltimateSealAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.WIND) || statsProps.getSecondAttribute().equals(ModValues.WIND))
        {
            gainAbility(event.getPlayer(), 11, WindBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ToweringTornadoAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, WindFlightAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, TornadoPiercingAbility.INSTANCE);
            gainAbility(event.getPlayer(), 30, WindGaleAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.LIGHT) || statsProps.getSecondAttribute().equals(ModValues.LIGHT))
        {
            gainAbility(event.getPlayer(), 10, LightBladeShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, LightHealingAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LightMovementAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.LIGHTNING) || statsProps.getSecondAttribute().equals(ModValues.LIGHTNING))
        {
            gainAbility(event.getPlayer(), 8, ThunderGodGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 10, ThunderCrumblingOrbAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ThunderSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, ThunderFiendMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, ThunderCrumblingOrbMultiAbility.INSTANCE);
            gainAbility(event.getPlayer(), 30, ThunderChargeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 35, ThunderDischargeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.FIRE) || statsProps.getSecondAttribute().equals(ModValues.FIRE))
        {
            gainAbility(event.getPlayer(), 10, SpiralFlameAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, SolLineaAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LeoPalmaAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, WildBurstingFlamesAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.EARTH) || statsProps.getSecondAttribute().equals(ModValues.EARTH))
        {
            gainAbility(event.getPlayer(), 10, EarthGlovesAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, EarthMinionAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, EarthGolemAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, EarthQuakeAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.DARKNESS) || statsProps.getSecondAttribute().equals(ModValues.DARKNESS))
        {
            gainAbility(event.getPlayer(), 10, BluntStrikeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, AvidyaWildSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, BlackHoleAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.SLASH) || statsProps.getSecondAttribute().equals(ModValues.SLASH))
        {
            gainAbility(event.getPlayer(), 10, DeathScytheShowerAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ForwardThrustAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, LunaticSlashAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, RoundLunaticSlashAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.TIME) || statsProps.getSecondAttribute().equals(ModValues.TIME))
        {
            gainAbility(event.getPlayer(), 10, TimeHealAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, ChronoStasisGrigoraAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, TimeHopAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, TimeRessurectionAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.BEAST) || statsProps.getSecondAttribute().equals(ModValues.BEAST))
        {
            gainAbility(event.getPlayer(), 10, CheetaChargeAbility.INSTANCE);
            gainAbility(event.getPlayer(), 15, JaguarsHunt.INSTANCE);
            gainAbility(event.getPlayer(), 20, HippopotamusBiteAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, RhinocerosArmorAbility.INSTANCE);
            gainAbility(event.getPlayer(), 30, LionsHowlAbility.INSTANCE);
        }
        if (statsProps.getAttribute().equals(ModValues.MERCURY) || statsProps.getSecondAttribute().equals(ModValues.MERCURY))
        {
            gainAbility(event.getPlayer(), 10, MercurySpearAbility.INSTANCE);
            gainAbility(event.getPlayer(), 20, MercuryBubbleAbility.INSTANCE);
            gainAbility(event.getPlayer(), 25, MercuryRainAbility.INSTANCE);
        }
    }

    private static void gainAbility(PlayerEntity player, int level, AbilityCore ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() + 1 >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            abilityProps.addUnlockedAbility(player, ability);
        }
        if ((props.getLevel() + 1 < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
