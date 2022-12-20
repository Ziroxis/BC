package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
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
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.sealing.SealingProjectileAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.util.UUID;

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
            //TODO don't spawn with anti-magic + second attribute
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
                case "Lightning":
                    abilityProps.addUnlockedAbility(ThunderGodBootsAbility.INSTANCE);
                    break;
                case "Darkness":
                    abilityProps.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
                    break;
                case "Earth":
                    abilityProps.addUnlockedAbility(EarthChunkAbility.INSTANCE);
                    break;
                case "Slash":
                    abilityProps.addUnlockedAbility(SlashBladesAbility.INSTANCE);
                    break;
                case "Sealing":
                    abilityProps.addUnlockedAbility(SealingProjectileAbility.INSTANCE);
                    break;
                case (ModValues.WATER):
                    abilityProps.addUnlockedAbility(WaterBallAbility.INSTANCE);
                    break;
                case (ModValues.ANTIMAGIC):
                    abilityProps.addUnlockedAbility(DemonSlayerAbility.INSTANCE);
                    abilityProps.addUnlockedAbility(BullThrustAbility.INSTANCE);
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
                if (race.equals(ModValues.HYBRID))
                {
                    props.setManaRegeneration(1);
                    props.setMultiplier(1);
                    do
                    {
                        props.setSecondAttribute(Beapi.randomizer(ModValues.attributes_no_antimagic));
                    }   while (props.getSecondAttribute().equals(props.getAttribute()));
                    String secondAttribute = props.getSecondAttribute();
                    switch (secondAttribute)
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
                        case "Lightning":
                            abilityProps.addUnlockedAbility(ThunderGodBootsAbility.INSTANCE);
                            break;
                        case "Darkness":
                            abilityProps.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
                            break;
                        case "Earth":
                            abilityProps.addUnlockedAbility(EarthChunkAbility.INSTANCE);
                            break;
                        case "Slash":
                            abilityProps.addUnlockedAbility(SlashBladesAbility.INSTANCE);
                            break;
                        case "Sealing":
                            abilityProps.addUnlockedAbility(SealingProjectileAbility.INSTANCE);
                            break;
                        case (ModValues.WATER):
                            abilityProps.addUnlockedAbility(WaterBallAbility.INSTANCE);
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
        if (uuid.toString().equals("b0515226-7ff3-4ab4-aa87-69179ee0e4ae")) // -> Beosti
            props.setTitle("\u00A71Almighty captain of Yuanno");
        else if (uuid.toString().equals("df9f1a55-1cff-43ad-9e3d-3bc03b6bd984")) // -> Kausu
            props.setTitle("§aSage");
        else if (uuid.toString().equals("649d12e3-da8c-4a30-b7b5-1c99376d261e")) // -> Redwolf
            props.setTitle("§cThe king");
        else if (uuid.toString().equals("9819c734-1928-4757-8724-801ced9218c6")) // -> Skyling
            props.setTitle("§3Engulfing sky");
        else if (uuid.toString().equals("b462e469-19ce-40b6-9999-d74102623b7c")) // -> Gingershadow
            props.setTitle("§4The true");
        else if (uuid.toString().equals("72336f87-461e-4e3b-a213-df3982241c39")) // -> Apollo
            props.setTitle("§0Wannabe rival");
        else if (uuid.toString().equals("0226c610-b3a4-4de0-b63a-2e5599b843f2")) // -> Blank
            props.setTitle("§7Beggar");
        else if (uuid.toString().equals("2f81171f-fbec-442b-9d6d-3b21cad56f09")) // -> Kiwi
            props.setTitle("§7Determined Soul");
        else if (uuid.toString().equals("2b01df92-48e7-4e6d-962d-c38e8e2a5fd0")) // -> Danal
            props.setTitle("§2True farmer");
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
