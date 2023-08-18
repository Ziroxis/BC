package com.yuanno.block_clover.init;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.quests.arank.HealingPotionQuest;
import com.yuanno.block_clover.quests.brank.KillEndermanQuest;
import com.yuanno.block_clover.quests.brank.ObtainGoldQuest;
import com.yuanno.block_clover.quests.crank.*;
import com.yuanno.block_clover.quests.drank.*;
import com.yuanno.block_clover.quests.magician.GrimoireQuest;
import com.yuanno.block_clover.quests.magician.ManaReinforcementQuest;
import com.yuanno.block_clover.quests.magician.ManaSkinQuest;
import com.yuanno.block_clover.quests.magician.ManaZoneQuest;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ModQuests {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static final QuestId HEALING_POTION = BeRegistry.registerQuest(HealingPotionQuest.INSTANCE);
    public static final List<QuestId> A_QUESTS = Arrays.asList(HEALING_POTION);

    public static final QuestId KILL_ENDERMAN = BeRegistry.registerQuest(KillEndermanQuest.INSTANCE);
    public static final QuestId OBTAIN_GOLD = BeRegistry.registerQuest(ObtainGoldQuest.INSTANCE);
    public static final List<QuestId> B_QUESTS = Arrays.asList(OBTAIN_GOLD, KILL_ENDERMAN);

    public static final QuestId SALMON_FISHING = BeRegistry.registerQuest(FishingSalmonQuest.INSTANCE);
    public static final QuestId WHEAT_HARVESTING = BeRegistry.registerQuest(WheatHarvestingQuest.INSTANCE);
    public static final QuestId KILL_CREEPER = BeRegistry.registerQuest(KillCreeperQuest.INSTANCE);
    public static final QuestId KILL_SKELETON = BeRegistry.registerQuest(KillSkeletonQuest.INSTANCE);
    public static final QuestId KILL_ZOMBIE = BeRegistry.registerQuest(KillZombieQuest.INSTANCE);
    public static final QuestId OBTAIN_IRON = BeRegistry.registerQuest(ObtainIronQuest.INSTANCE);
    public static final List<QuestId> C_QUESTS = Arrays.asList(OBTAIN_IRON, KILL_CREEPER, KILL_SKELETON, KILL_ZOMBIE, WHEAT_HARVESTING, SALMON_FISHING);

    public static final QuestId SEED_HARVESTING = BeRegistry.registerQuest(SeedHarvestingQuest.INSTANCE);
    public static final QuestId OBTAIN_COAL = BeRegistry.registerQuest(ObtainCoalQuest.INSTANCE);
    public static final List<QuestId> D_QUESTS = Arrays.asList(OBTAIN_COAL, SEED_HARVESTING);

    public static final List<QuestId> mergedListQuestBoard = setMergedList();

    public static List<QuestId> setMergedList()
    {
        List<QuestId> mergedList = new ArrayList<>();
        mergedList.addAll(A_QUESTS);
        mergedList.addAll(B_QUESTS);
        mergedList.addAll(C_QUESTS);
        mergedList.addAll(D_QUESTS);
        return mergedList;
    }

    public static final QuestId GRIMOIRE = BeRegistry.registerQuest(GrimoireQuest.INSTANCE);
    public static final QuestId MANA_REINFORCEMENT = BeRegistry.registerQuest(ManaReinforcementQuest.INSTANCE);
    public static final QuestId MANA_SKIN = BeRegistry.registerQuest(ManaSkinQuest.INSTANCE);
    public static final QuestId MANA_ZONE = BeRegistry.registerQuest(ManaZoneQuest.INSTANCE);
    public static final List<QuestId> MAGICIAN = Arrays.asList(GRIMOIRE, MANA_REINFORCEMENT, MANA_SKIN, MANA_ZONE);
    public static void register(IEventBus eventBus)
    {

    }
}
