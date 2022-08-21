package com.yuanno.block_clover.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
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
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.sealing.OtherHealSealingAbility;
import com.yuanno.block_clover.spells.sealing.SealingPunchAbility;
import com.yuanno.block_clover.spells.sealing.SelfHealSealingAbility;
import com.yuanno.block_clover.spells.sealing.UltimateSealAbility;
import com.yuanno.block_clover.spells.slash.*;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

public class RaceCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("race").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("RACE", StringArgumentType.string()).suggests(SUGGEST_SET)
                                        .executes((command) ->
                                        {

                                            String set = StringArgumentType.getString(command, "RACE");

                                            return setRace(command.getSource(), EntityArgument.getPlayer(command, "target"), set);
                                        }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("ELF");
        suggestions.add("HUMAN");
        suggestions.add("HYBRID");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setRace(CommandSource command, PlayerEntity player, String set)
    {
        IEntityStats statsProps = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);

        if (statsProps.getRace().equals(ModValues.ELF))
            statsProps.alterManaRegeneration(-1);
        else if (statsProps.getRace().equals(ModValues.HYBRID))
        {
            statsProps.setSecondAttribute("");
            abilityData.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
            if (statsProps.hasGrimoire()) 
            {
                if (statsProps.getAttribute().equals(ModValues.SEALING))
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
            }
        }
        switch (set)
        {
            case "ELF":
                statsProps.setRace(ModValues.ELF);
                break;
            case "HUMAN":
                statsProps.setRace(ModValues.HUMAN);
                break;
            case "HYBRID":
                statsProps.setRace(ModValues.HYBRID);
                break;
        }

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);

        return 1;
    }

    private static void gainAbility(PlayerEntity player, int level, Ability ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            abilityProps.addUnlockedAbility(ability);
        }
        if ((props.getLevel() < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
