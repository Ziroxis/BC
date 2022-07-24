package com.example.block_clover.events;

import com.example.block_clover.Main;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.data.quest.IQuestData;
import com.example.block_clover.data.quest.QuestDataCapability;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class DyingEvents {

    @SubscribeEvent
    public static void onRelogging(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);
        //Nothing
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            IAbilityData newAbilityData = AbilityDataCapability.get(event.getPlayer());
            INBT nbt = new CompoundNBT();
            DyingEvents.restoreFullData(event.getOriginal(), event.getPlayer());
            DyingEvents.restorePermaData(event.getOriginal(), event.getPlayer());
            PacketHandler.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getId(), newAbilityData), event.getPlayer());
        }
        else
            DyingEvents.restoreFullData(event.getOriginal(), event.getPlayer());
    }

    private static void restoreFullData(PlayerEntity original, PlayerEntity player)
    {
        IAbilityData newAbilityData = AbilityDataCapability.get(player);
        INBT nbt = new CompoundNBT();

        // Keep the entity stats
        IEntityStats oldEntityStats = EntityStatsCapability.get(original);
        nbt = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
        IEntityStats newEntityStats = EntityStatsCapability.get(player);
        EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, nbt);


        // Keep the ability stats
        IAbilityData oldAbilityData = AbilityDataCapability.get(original);
        nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
        AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);

        DyingEvents.restorePermaData(original, player);

    }
    private static void restorePermaData(PlayerEntity original, PlayerEntity player)
    {
        INBT nbt = new CompoundNBT();
        // Quest data is persisted no matter the config option.
        // Keep the quests data
        /*
        IQuestData oldQuestData = QuestDataCapability.get(original);
        nbt = QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
        IQuestData newQuestData = QuestDataCapability.get(player);
        QuestDataCapability.INSTANCE.readNBT(newQuestData, null, nbt);

         */

        // Keep the abilities
        IAbilityData oldAbilityData = AbilityDataCapability.get(original);
        oldAbilityData.clearEquippedAbilities(AbilityCategories.AbilityCategory.ALL);

        nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
        IAbilityData newAbilityData = AbilityDataCapability.get(player);
        AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);


    }
}
