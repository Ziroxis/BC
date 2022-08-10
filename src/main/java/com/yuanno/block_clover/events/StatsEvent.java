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
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.MinecraftForge;
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
                case "Anti-magic":
                    abilityProps.addUnlockedAbility(DemonSlayerAbility.INSTANCE);
                    abilityProps.addUnlockedAbility(BullThrustAbility.INSTANCE);
                    props.setRace(ModValues.HUMAN);
                    break;
            }
            if (!attribute.equals(ModValues.ANTIMAGIC))
            {
                props.setRace(Beapi.randomizer(ModValues.races));
                String race = props.getRace();
                if (race.equals(ModValues.HYBRID))
                {
                    props.setSecondAttribute(Beapi.randomizer(ModValues.attributes_no_antimagic));
                    if (props.getSecondAttribute().equals(props.getAttribute()))
                        props.setSecondAttribute(Beapi.randomizer(ModValues.attributes_no_antimagic));
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
                    }
                }
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
