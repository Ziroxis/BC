package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncQuestDataPacket;
import com.yuanno.block_clover.networking.server.SSyncWorldDataPacket;
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.beast.BearClawAbility;
import com.yuanno.block_clover.spells.copy.CopyAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.mercury.MercuryBulletAbility;
import com.yuanno.block_clover.spells.sealing.SealingProjectileAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.sword.AirDashAbility;
import com.yuanno.block_clover.spells.time.TimeStealAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Array;
import java.util.*;

import static com.yuanno.block_clover.api.Beapi.randomAttributeString;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsEvent {

    @SubscribeEvent
    public static void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);
        questData.addInProgressQuest(ModQuests.GRIMOIRE);
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        props.setMultiplier(1);
        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), player);
        if (!props.hasAttribute())
        {
            //TODO don't spawn with anti-magic + second attribute
            String attribute = randomAttributeString();
            props.setAttribute(attribute);
            // Create a HashMap to store the mapping between attribute values and abilities
            Map<String, AbilityCore> abilityMap = new HashMap<>();
            abilityMap.put(ModValues.WIND, WindBladeAbility.INSTANCE);
            abilityMap.put(ModValues.FIRE, FireBallAbility.INSTANCE);
            abilityMap.put(ModValues.LIGHT, LightBladeAbility.INSTANCE);
            abilityMap.put(ModValues.LIGHTNING, ThunderGodBootsAbility.INSTANCE);
            abilityMap.put(ModValues.DARKNESS, DarkCloakedBladeAbility.INSTANCE);
            abilityMap.put(ModValues.EARTH, EarthChunkAbility.INSTANCE);
            abilityMap.put(ModValues.SLASH, SlashBladesAbility.INSTANCE);
            abilityMap.put(ModValues.SEALING, SealingProjectileAbility.INSTANCE);
            abilityMap.put(ModValues.TIME, TimeStealAbility.INSTANCE);
            abilityMap.put(ModValues.WATER, WaterBallAbility.INSTANCE);
            abilityMap.put(ModValues.MERCURY, MercuryBulletAbility.INSTANCE);
            abilityMap.put(ModValues.BEAST, BearClawAbility.INSTANCE);
            abilityMap.put(ModValues.COPY, CopyAbility.INSTANCE);
            abilityMap.put(ModValues.SWORD, AirDashAbility.INSTANCE);
            abilityMap.put(ModValues.ANTIMAGIC, null); // Placeholder, as this case has multiple abilities

            // Get the ability based on the attribute value from the HashMap
            AbilityCore ability = abilityMap.get(attribute);

            // Add the unlocked ability to the player
            if (ability != null) {
                abilityProps.addUnlockedAbility(player, ability);

                // Additional operations for specific cases
                if (attribute.equals(ModValues.ANTIMAGIC)) {
                    abilityProps.addUnlockedAbility(player, DemonSlayerAbility.INSTANCE);
                    abilityProps.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
                    props.setRace(ModValues.HUMAN);
                }
            }

            // TODO check if you can optimize this code
            if (!attribute.equals(ModValues.ANTIMAGIC))
            {
                props.setRace(Beapi.randomizer(ModValues.races));
                String race = props.getRace();
                switch (race) {
                    // Humans get -> 0.2 xp multiplier; chance to be innate devil; mana regen = 1
                    case ModValues.HUMAN:
                        props.setManaRegeneration(1);
                        props.setMultiplier(1.2f);
                        int chanceDevil = Beapi.RNG(5);
                        if (chanceDevil == 0) {
                            props.setInnateDevil(true);
                            props.setDevil(Beapi.randomizer(ModValues.devils));
                        }
                        break;
                    // Elf get mana regeneration = 2; multiplier = 1
                    case ModValues.ELF:
                        props.setManaRegeneration(2);
                        props.setMultiplier(1);
                        break;
                    // Witch get mana regeneration = 1; xp multiplier = 0.3
                    case ModValues.WITCH:
                        props.setManaRegeneration(1);
                        props.setMultiplier(1.3f);
                        break;
                }
                if (race.equals(ModValues.HYBRID)) {
                    props.setManaRegeneration(1);
                    props.setMultiplier(1);

                    // Create a list of available attributes (excluding the current attribute)
                    List<String> availableAttributes = new ArrayList<>(Arrays.asList(
                            ModValues.WIND, ModValues.FIRE, ModValues.LIGHT, ModValues.LIGHTNING, ModValues.DARKNESS,
                            ModValues.EARTH, ModValues.SLASH, ModValues.SEALING, ModValues.TIME,
                            ModValues.WATER, ModValues.MERCURY, ModValues.BEAST, ModValues.COPY, ModValues.SWORD
                    ));

                    // Remove the current attribute from the available attributes list
                    availableAttributes.remove(props.getAttribute());

                    // Randomly select the second attribute from the remaining available attributes
                    String secondAttribute = availableAttributes.get(new Random().nextInt(availableAttributes.size()));

                    // Map secondAttribute to its corresponding ability using a HashMap
                    Map<String, AbilityCore> abilityMapSecond = new HashMap<>();
                    abilityMapSecond.put(ModValues.WIND, WindBladeAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.FIRE, FireBallAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.LIGHT, LightBladeAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.LIGHTNING, ThunderGodBootsAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.DARKNESS, DarkCloakedBladeAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.EARTH, EarthChunkAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.SLASH, SlashBladesAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.SEALING, SealingProjectileAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.TIME, TimeStealAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.WATER, WaterBallAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.MERCURY, MercuryBulletAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.BEAST, BearClawAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.COPY, CopyAbility.INSTANCE);
                    abilityMapSecond.put(ModValues.SWORD, AirDashAbility.INSTANCE);

                    // Get the ability based on the secondAttribute value from the HashMap
                    AbilityCore abilitySecond = abilityMapSecond.get(secondAttribute);

                    // Add the unlocked ability to the player
                    if (abilitySecond != null) {
                        abilityProps.addUnlockedAbility(player, abilitySecond);
                    }
                }

            }
            props.setLevel(1);
            props.setExperience(0);
            props.setMaxExperience(100);
            if (!attribute.equals(ModValues.ANTIMAGIC))
            {
                if (!props.getRace().equals(ModValues.ELF))
                {
                    props.setMana(50);
                    props.setMaxMana(50);
                }
                else
                {
                    props.setMaxMana(60);
                    props.setMana(60);
                }
            }
            else
            {
                props.setMana(0);
                props.setMaxMana(0);
            }
        }
        UUID uuid = player.getUUID();
        switch (uuid.toString()) {
            case "b0515226-7ff3-4ab4-aa87-69179ee0e4ae":
// -> Beosti
                props.setTitle("\u00A71Almighty captain of Yuanno");
                break;
            case "df9f1a55-1cff-43ad-9e3d-3bc03b6bd984":
// -> Kausu
                props.setTitle("§aSage");
                break;
            case "649d12e3-da8c-4a30-b7b5-1c99376d261e":
// -> Redwolf
                props.setTitle("§cThe king");
                break;
            case "b462e469-19ce-40b6-9999-d74102623b7c":
// -> Gingershadow
                props.setTitle("§4The true");
                break;
            case "72336f87-461e-4e3b-a213-df3982241c39":
// -> Apollo
                props.setTitle("§0Wannabe rival");
                break;
            case "0226c610-b3a4-4de0-b63a-2e5599b843f2":
// -> Blank
                props.setTitle("§7Beggar");
                break;
            case "2f81171f-fbec-442b-9d6d-3b21cad56f09":
// -> Kiwi
                props.setTitle("§7Determined Soul");
                break;
            case "2b01df92-48e7-4e6d-962d-c38e8e2a5fd0":
// -> Danal
                props.setTitle("§2True farmer");
                break;
        }
        PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }

    @SubscribeEvent
    public static void onRelogging(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity player = event.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            StatsEvent.restoreFullData(event.getOriginal(), event.getPlayer());
            IAbilityData newAbilityData = AbilityDataCapability.get(event.getPlayer());
            IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
            PacketHandler.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getId(), newAbilityData), event.getPlayer());
            ExperienceUpEvent e = new ExperienceUpEvent(event.getPlayer(), newEntityStats.getExperience());
            MinecraftForge.EVENT_BUS.post(e);
        } else
            StatsEvent.restoreFullData(event.getOriginal(), event.getPlayer());
    }
    private static void restoreFullData(PlayerEntity original, PlayerEntity player) {
        INBT nbt = new CompoundNBT();

        // Keep the entity stats
        IEntityStats oldEntityStats = EntityStatsCapability.get(original);
        nbt = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
        IEntityStats newEntityStats = EntityStatsCapability.get(player);
        EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, nbt);
        ExperienceUpEvent event = new ExperienceUpEvent(player, newEntityStats.getExperience());
        MinecraftForge.EVENT_BUS.post(event);


        // Keep the ability stats
        IAbilityData oldAbilityData = AbilityDataCapability.get(original);
        oldAbilityData.clearEquippedAbilities(AbilityCategories.AbilityCategory.ALL);

        nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
        IAbilityData newAbilityData = AbilityDataCapability.get(player);
        AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);


        /*
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), newAbilityData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), newEntityStats), player);

         */


    }

    @SubscribeEvent
    public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats stats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);

        PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), stats), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        //MinecraftForge.EVENT_BUS.post(new EntityEvent.Size(player, player.getPose(), player.getDimensions(player.getPose()), player.getBbHeight()));
    }

    @SubscribeEvent
    public static void onPlayerStartsTracking(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof  PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getTarget();
            IEntityStats stats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);

            PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), stats), player);
            PacketHandler.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityData), player);

        }
    }

}
