package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.items.ArtifactItem;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.darkness.*;
import com.yuanno.block_clover.spells.earth.*;
import com.yuanno.block_clover.spells.fire.*;
import com.yuanno.block_clover.spells.light.*;
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.sealing.*;
import com.yuanno.block_clover.spells.slash.*;
import com.yuanno.block_clover.spells.time.*;
import com.yuanno.block_clover.spells.water.*;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RaceChangeArtifactItem extends ArtifactItem {

    public RaceChangeArtifactItem()
    {
        this.artifactInformation = "Changes the race of the user of the artifact";
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            IEntityStats statsProps = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);

            if (statsProps.getRace().equals(ModValues.HYBRID))
            {
                statsProps.setSecondAttribute("");
                abilityData.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
                if (statsProps.hasGrimoire())
                {
                    if (statsProps.getSecondAttribute().equals(ModValues.SEALING))
                    {
                        gainAbility(player, 5, SelfHealSealingAbility.INSTANCE);
                        gainAbility(player, 10, SealingPunchAbility.INSTANCE);
                        gainAbility(player, 15, OtherHealSealingAbility.INSTANCE);
                        gainAbility(player, 20, UltimateSealAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.WIND)) {
                        gainAbility(player, 3, WindCrescentAbility.INSTANCE);
                        gainAbility(player, 7, WindBladeShowerAbility.INSTANCE);
                        gainAbility(player, 15, ToweringTornadoAbility.INSTANCE);
                        gainAbility(player, 20, WindFlightAbility.INSTANCE);
                        gainAbility(player, 25, TornadoPiercingAbility.INSTANCE);
                        gainAbility(player, 30, WindGaleAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.LIGHT)) {
                        gainAbility(player, 5, LightSwordAbility.INSTANCE);
                        gainAbility(player, 10, LightBladeShowerAbility.INSTANCE);
                        gainAbility(player, 15, LightHealingAbility.INSTANCE);
                        gainAbility(player, 20, LightMovementAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.LIGHTNING)) {
                        gainAbility(player, 3, ThunderFiendAbility.INSTANCE);
                        gainAbility(player, 5, ThunderGodGlovesAbility.INSTANCE);
                        gainAbility(player, 10, ThunderCrumblingOrbAbility.INSTANCE);
                        gainAbility(player, 15, ThunderSlashAbility.INSTANCE);
                        gainAbility(player, 20, ThunderFiendMultiAbility.INSTANCE);
                        gainAbility(player, 25, ThunderCrumblingOrbMultiAbility.INSTANCE);
                        gainAbility(player, 30, ThunderChargeAbility.INSTANCE);
                        gainAbility(player, 35, ThunderDischargeAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.FIRE)) {
                        gainAbility(player, 5, FlameRoarAbility.INSTANCE);
                        gainAbility(player, 10, SpiralFlameAbility.INSTANCE);
                        gainAbility(player, 15, SolLineaAbility.INSTANCE);
                        gainAbility(player, 20, LeoPalmaAbility.INSTANCE);
                        gainAbility(player, 25, WildBurstingFlamesAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.EARTH)) {
                        gainAbility(player, 3, EarthPassiveAbility.INSTANCE);
                        gainAbility(player, 5, EarthChargeAbility.INSTANCE);
                        gainAbility(player, 10, EarthGlovesAbility.INSTANCE);
                        gainAbility(player, 15, EarthMinionAbility.INSTANCE);
                        gainAbility(player, 20, EarthGolemAbility.INSTANCE);
                        gainAbility(player, 25, EarthQuakeAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.DARKNESS)) {
                        gainAbility(player, 3, AvidyaSlashAbility.INSTANCE);
                        gainAbility(player, 10, BluntStrikeAbility.INSTANCE);
                        gainAbility(player, 15, AvidyaWildSlashAbility.INSTANCE);
                        gainAbility(player, 25, BlackHoleAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.SLASH)) {
                        gainAbility(player, 3, DeathScytheAbility.INSTANCE);
                        gainAbility(player, 5, DeathScytheShowerAbility.INSTANCE);
                        gainAbility(player, 10, ForwardThrustAbility.INSTANCE);
                        gainAbility(player, 15, LunaticSlashAbility.INSTANCE);
                        gainAbility(player, 20, RoundLunaticSlashAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.WATER)) {
                        gainAbility(player, 5, WaterShieldAbility.INSTANCE);
                        gainAbility(player, 10, WaterSpearAbility.INSTANCE);
                        gainAbility(player, 15, WaterDragonAbility.INSTANCE);
                        gainAbility(player, 20, ValkyrieArmorAbility.INSTANCE);
                        gainAbility(player, 25, PointBlankDragonAbility.INSTANCE);
                    }
                    if (statsProps.getAttribute().equals(ModValues.TIME)) {
                        gainAbility(player, 5, ChronoStasisAbility.INSTANCE);
                        gainAbility(player, 10, TimeHealAbility.INSTANCE);
                        gainAbility(player, 15, ChronoStasisGrigoraAbility.INSTANCE);
                        gainAbility(player, 20, TimeHopAbility.INSTANCE);
                        gainAbility(player, 25, TimeRessurectionAbility.INSTANCE);
                    }
                }
                else
                {
                    switch (statsProps.getAttribute())
                    {
                        case "Wind":
                            abilityData.addUnlockedAbility(player, WindBladeAbility.INSTANCE);
                            break;
                        case "Fire":
                            abilityData.addUnlockedAbility(player, FireBallAbility.INSTANCE);
                            break;
                        case "Light":
                            abilityData.addUnlockedAbility(player, LightBladeAbility.INSTANCE);
                            break;
                        case "Lightning":
                            abilityData.addUnlockedAbility(player, ThunderGodBootsAbility.INSTANCE);
                            break;
                        case "Darkness":
                            abilityData.addUnlockedAbility(player, DarkCloakedBladeAbility.INSTANCE);
                            break;
                        case "Earth":
                            abilityData.addUnlockedAbility(player, EarthChunkAbility.INSTANCE);
                            break;
                        case "Slash":
                            abilityData.addUnlockedAbility(player, SlashBladesAbility.INSTANCE);
                            break;
                        case "Sealing":
                            abilityData.addUnlockedAbility(player, SealingProjectileAbility.INSTANCE);
                            break;
                        case "Time":
                            abilityData.addUnlockedAbility(player, TimeStealAbility.INSTANCE);
                            break;
                        case "Water":
                            abilityData.addUnlockedAbility(player, WaterBallAbility.INSTANCE);
                            break;
                    }
                }
            }
            statsProps.setRace(BeJavapi.randomizer(ModValues.races));
            switch (statsProps.getRace())
            {
                case (ModValues.ELF):
                    statsProps.setRace(ModValues.ELF);
                    if (statsProps.getLevel() > 1)
                    {
                    statsProps.setManaRegeneration(2 + statsProps.getLevel());
                    statsProps.setMaxMana(50 + 10 * statsProps.getLevel());
                    }
                    else
                    {
                        statsProps.setManaRegeneration(2);
                        statsProps.setMaxMana(50 + 10);
                    }
                    break;
                case (ModValues.HUMAN):
                    statsProps.setRace(ModValues.HUMAN);
                    if (statsProps.getLevel() > 1)
                     {
                    statsProps.setManaRegeneration(1 + (float) statsProps.getLevel() / 2);
                    statsProps.setMaxMana(50 + 5 * statsProps.getLevel());
                     }
                    break;
                case (ModValues.HYBRID):
                    statsProps.setRace(ModValues.HYBRID);
                    if (statsProps.getLevel() > 1)
                    {
                    statsProps.setManaRegeneration(1 + (float) statsProps.getLevel() / 2);
                    statsProps.setMaxMana(50 + 5 * statsProps.getLevel());
                    }
                    do
                    {
                        statsProps.setSecondAttribute(BeJavapi.randomizer(ModValues.attributes_no_antimagic));
                    }   while (statsProps.getSecondAttribute().equals(statsProps.getAttribute()));
                    String secondAttribute = statsProps.getSecondAttribute();
                    switch (secondAttribute)
                    {
                        case "Wind":
                            abilityData.addUnlockedAbility(player, WindBladeAbility.INSTANCE);
                            break;
                        case "Fire":
                            abilityData.addUnlockedAbility(player, FireBallAbility.INSTANCE);
                            break;
                        case "Light":
                            abilityData.addUnlockedAbility(player, LightBladeAbility.INSTANCE);
                            break;
                        case "Lightning":
                            abilityData.addUnlockedAbility(player, ThunderGodBootsAbility.INSTANCE);
                            break;
                        case "Darkness":
                            abilityData.addUnlockedAbility(player, DarkCloakedBladeAbility.INSTANCE);
                            break;
                        case "Earth":
                            abilityData.addUnlockedAbility(player, EarthChunkAbility.INSTANCE);
                            break;
                        case "Slash":
                            abilityData.addUnlockedAbility(player, SlashBladesAbility.INSTANCE);
                            break;
                        case "Sealing":
                            abilityData.addUnlockedAbility(player, SealingProjectileAbility.INSTANCE);
                            break;
                        case "Time":
                            abilityData.addUnlockedAbility(player, TimeStealAbility.INSTANCE);
                            break;
                        case "Water":
                            abilityData.addUnlockedAbility(player, WaterBallAbility.INSTANCE);
                            break;
                    }
                    break;
            }


            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }
        itemStack.shrink(1);
        return ActionResult.sidedSuccess(itemStack, world.isClientSide());
    }

    private static void gainAbility(PlayerEntity player, int level, AbilityCore ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            abilityProps.addUnlockedAbility(player, ability);
        }
        if ((props.getLevel() < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
