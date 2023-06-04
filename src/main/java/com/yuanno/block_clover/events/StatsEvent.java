package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsEvent {

    @SubscribeEvent
    public static void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), player);
        //TODO add the new attribute starting spells
        if (!props.hasAttribute())
        {
            //TODO don't spawn with anti-magic + second attribute
            props.setAttribute(randomAttributeString());
            String attribute = props.getAttribute();
            switch (attribute)
            {
                case "Wind":
                    abilityProps.addUnlockedAbility(player, WindBladeAbility.INSTANCE);
                    break;
                case "Fire":
                    abilityProps.addUnlockedAbility(player, FireBallAbility.INSTANCE);
                    break;
                case "Light":
                    abilityProps.addUnlockedAbility(player, LightBladeAbility.INSTANCE);
                    break;
                case "Lightning":
                    abilityProps.addUnlockedAbility(player, ThunderGodBootsAbility.INSTANCE);
                    break;
                case "Darkness":
                    abilityProps.addUnlockedAbility(player, DarkCloakedBladeAbility.INSTANCE);
                    break;
                case "Earth":
                    abilityProps.addUnlockedAbility(player, EarthChunkAbility.INSTANCE);
                    break;
                case "Slash":
                    abilityProps.addUnlockedAbility(player, SlashBladesAbility.INSTANCE);
                    break;
                case "Sealing":
                    abilityProps.addUnlockedAbility(player, SealingProjectileAbility.INSTANCE);
                    break;
                case "Time":
                    abilityProps.addUnlockedAbility(player, TimeStealAbility.INSTANCE);
                    break;
                case "Water":
                    abilityProps.addUnlockedAbility(player, WaterBallAbility.INSTANCE);
                    break;
                case (ModValues.MERCURY):
                    abilityProps.addUnlockedAbility(player, MercuryBulletAbility.INSTANCE);
                    break;
                case (ModValues.BEAST):
                    abilityProps.addUnlockedAbility(player, BearClawAbility.INSTANCE);
                    break;
                case (ModValues.COPY):
                    abilityProps.addUnlockedAbility(player, CopyAbility.INSTANCE);
                    break;
                case (ModValues.SWORD):
                    abilityProps.addUnlockedAbility(player, AirDashAbility.INSTANCE);
                    break;
                case (ModValues.ANTIMAGIC):
                    abilityProps.addUnlockedAbility(player, DemonSlayerAbility.INSTANCE);
                    abilityProps.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
                    props.setRace(ModValues.HUMAN);
                    break;
            }
            if (!attribute.equals(ModValues.ANTIMAGIC))
            {
                props.setRace(Beapi.randomizer(ModValues.races));
                String race = props.getRace();
                if (race.equals(ModValues.HUMAN))
                {
                    props.setManaRegeneration(1);
                    props.setMultiplier(1.2f);
                }
                else if (race.equals(ModValues.ELF))
                {
                    props.setManaRegeneration(2);
                    props.setMultiplier(1);
                }
                else if (race.equals(ModValues.WITCH))
                {
                    props.setManaRegeneration(1);
                    props.setMultiplier(1.3f);
                }
                if (race.equals(ModValues.HYBRID))
                {
                    props.setManaRegeneration(1);
                    props.setMultiplier(1);
                    do
                    {
                        props.setSecondAttribute(randomAttributeString());
                    }   while (props.getSecondAttribute().equals(props.getAttribute()));
                    String secondAttribute = props.getSecondAttribute();
                    switch (secondAttribute)
                    {
                        case "Wind":
                            abilityProps.addUnlockedAbility(player, WindBladeAbility.INSTANCE);
                            break;
                        case "Fire":
                            abilityProps.addUnlockedAbility(player, FireBallAbility.INSTANCE);
                            break;
                        case "Light":
                            abilityProps.addUnlockedAbility(player, LightBladeAbility.INSTANCE);
                            break;
                        case "Lightning":
                            abilityProps.addUnlockedAbility(player, ThunderGodBootsAbility.INSTANCE);
                            break;
                        case "Darkness":
                            abilityProps.addUnlockedAbility(player, DarkCloakedBladeAbility.INSTANCE);
                            break;
                        case "Earth":
                            abilityProps.addUnlockedAbility(player, EarthChunkAbility.INSTANCE);
                            break;
                        case "Slash":
                            abilityProps.addUnlockedAbility(player, SlashBladesAbility.INSTANCE);
                            break;
                        case "Sealing":
                            abilityProps.addUnlockedAbility(player, SealingProjectileAbility.INSTANCE);
                            break;
                        case "Time":
                            abilityProps.addUnlockedAbility(player, TimeStealAbility.INSTANCE);
                            break;
                        case (ModValues.WATER):
                            abilityProps.addUnlockedAbility(player, WaterBallAbility.INSTANCE);
                            break;
                        case (ModValues.MERCURY):
                            abilityProps.addUnlockedAbility(player, MercuryBulletAbility.INSTANCE);
                            break;
                        case (ModValues.BEAST):
                            abilityProps.addUnlockedAbility(player, BearClawAbility.INSTANCE);
                            break;
                        case (ModValues.COPY):
                            abilityProps.addUnlockedAbility(player, CopyAbility.INSTANCE);
                            break;
                        case (ModValues.SWORD):
                            abilityProps.addUnlockedAbility(player, AirDashAbility.INSTANCE);
                            break;
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

    public static String randomAttributeString()
    {
        String attribute = "";

        List<List<String>> elementalAttributes = new ArrayList<>();

        List<String> elemental = Arrays.asList(ModValues.DARKNESS, ModValues.EARTH, ModValues.FIRE, ModValues.LIGHT, ModValues.WATER, ModValues.WIND);
        elementalAttributes.add(elemental);
        List<String> subElemental = Arrays.asList(ModValues.LIGHTNING, ModValues.MERCURY);
        elementalAttributes.add(subElemental);

        List<List<String>> arcaneAttributes = new ArrayList<>();
        List<String> normalArcane = Arrays.asList(ModValues.BEAST, ModValues.SEALING, ModValues.SLASH);
        arcaneAttributes.add(normalArcane);
        List<String> specialArcane = Arrays.asList(ModValues.COPY, ModValues.SWORD, ModValues.TIME);
        arcaneAttributes.add(specialArcane);

        List<String> specialCase = Arrays.asList(ModValues.ANTIMAGIC);

        List<List<String>> allMixedAttributes = new ArrayList<>();
        allMixedAttributes.addAll(elementalAttributes);
        allMixedAttributes.addAll(arcaneAttributes);

        double groupElementalWeight = 0.5;
        double groupArcaneWeight = 0.5;

        // randomly select between group A and group B based on their weights
        List<List<String>> chosenGroup = Math.random() < groupElementalWeight ? elementalAttributes : arcaneAttributes;

        // set weights for each subgroup
        double subgroupNormalArcaneWeight = 0.7;
        double subgroupSpecialArcaneWeight = 0.3;
        double subgroupElementalWeight = 0.6;
        double subgroupSubElementalWeight = 0.4;

        // randomly select between the two subgroups within the chosen group based on their weights
        List<String> chosenSubgroup;
        if (chosenGroup == arcaneAttributes) {
            chosenSubgroup = Math.random() < subgroupNormalArcaneWeight ? normalArcane : specialArcane;
        } else {
            chosenSubgroup = Math.random() < subgroupElementalWeight ? elemental : subElemental;
        }

        // randomly select an attribute from the chosen subgroup
        String chosenAttribute = chosenSubgroup.get((int) (Math.random() * chosenSubgroup.size()));

        // randomly select an attribute from the specialCase list with a 5% chance
        if (Math.random() < 0.05) {
            chosenAttribute = specialCase.get((int) (Math.random() * specialCase.size()));
        }

        attribute = chosenAttribute;
        return attribute;
    }
}
