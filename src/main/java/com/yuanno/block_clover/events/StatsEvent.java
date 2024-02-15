package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.entities.devils.DevilEntity;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsEvent {

    private static ArrayList<String> attributes = new ArrayList<String>();
    @SubscribeEvent
    public static void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);
        IDevil devil = DevilCapability.get(player);
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);

        if (!props.hasAttribute())
            statsHandling(player);
        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), player);
        PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);

    }

    public static void statsHandling(PlayerEntity player)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        IDevil devil = DevilCapability.get(player);
        if (props.hasAttribute())
            return;


        props.setMultiplier(1);
        props.setLevel(1);
        props.setExperience(0);
        props.setMaxExperience(100);
        props.setYule(0);
        ArrayList<String> attributes = Beapi.randomAttributes();
        if (props.getChosenAttributes().isEmpty()) {
            props.setChosenAttributes(attributes);
            PacketHandler.sendTo(new SOpenAttributeChoiceScreenPacket(attributes), player);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        }
        else
            PacketHandler.sendTo(new SOpenAttributeChoiceScreenPacket(props.getChosenAttributes()), player);


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
        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), player);
        PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);

    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event)
    {

        // the killed one is player; the killer is devil; check side
        if (!(event.getSource().getEntity() instanceof DevilEntity) || !(event.getEntityLiving() instanceof PlayerEntity) || event.getSource().getEntity().level.isClientSide)
            return;
        PlayerEntity playerTarget = (PlayerEntity) event.getEntityLiving();
        DevilEntity devilEntity = (DevilEntity) event.getSource().getEntity();
        IEntityStats entityStats = EntityStatsCapability.get(playerTarget);
        entityStats.fullReset();
        IAbilityData abilityData = AbilityDataCapability.get(playerTarget);
        abilityData.fullReset();
        IQuestData questData = QuestDataCapability.get(playerTarget);
        questData.fullReset();
        IDevil devil = DevilCapability.get(playerTarget);
        devil.fullReset();
        PacketHandler.sendTo(new SSyncQuestDataPacket(playerTarget.getId(), questData), playerTarget);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(playerTarget.getId(), entityStats), playerTarget);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(playerTarget.getId(), abilityData), playerTarget);
        PacketHandler.sendTo(new SSyncDevilPacket(playerTarget.getId(), devil), playerTarget);

    }
    @SubscribeEvent
    public static void onRelogging(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity player = event.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);
        IDevil devil = DevilCapability.get(player);

        if (!statsProps.hasAttribute())
            statsHandling(player);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);
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
        //oldAbilityData.clearEquippedAbilities(AbilityCategories.AbilityCategory.ALL);
        nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
        IAbilityData newAbilityData = AbilityDataCapability.get(player);
        AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);

        IQuestData oldQuestData = QuestDataCapability.get(original);
        nbt = QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
        IQuestData newQuestData = QuestDataCapability.get(player);
        QuestDataCapability.INSTANCE.readNBT(newQuestData, null, nbt);

        IDevil oldDevilData = DevilCapability.get(original);
        nbt = DevilCapability.INSTANCE.writeNBT(oldDevilData, null);
        IDevil newDevilData = DevilCapability.get(player);
        DevilCapability.INSTANCE.readNBT(newDevilData, null, nbt);

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
        IDevil devil = DevilCapability.get(player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), stats), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncDevilPacket(player.getId(), devil), player);
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
