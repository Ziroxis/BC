package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestRegistry;
import com.yuanno.block_clover.quests.crank.ObtainIronQuest;
import com.yuanno.block_clover.quests.drank.*;
import com.yuanno.block_clover.quests.magician.GrimoireQuest;
import com.yuanno.block_clover.quests.magician.ManaReinforcementQuest;
import com.yuanno.block_clover.quests.magician.ManaSkinQuest;
import com.yuanno.block_clover.quests.magician.ManaZoneQuest;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModQuests {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(QuestRegistry.QUESTS, Main.MODID);

    public static final Quest OBTAIN_IRON = new ObtainIronQuest();
    public static final Quest[] C_QUEST = new Quest[] {OBTAIN_IRON};

    public static final Quest OBTAIN_COAL = new ObtainCoalQuest();
    public static final Quest KILL_SPIDER = new KillSpiderQuest();
    public static final Quest KILL_ZOMBIE = new KillZombieQuest();
    public static final Quest KILL_SKELETON = new KillSkeletonQuest();
    public static final Quest KILL_CREEPER = new KillCreeperQuest();
    public static final Quest[] D_QUESTS = new Quest[] {KILL_CREEPER, KILL_SKELETON, KILL_ZOMBIE, KILL_SPIDER, OBTAIN_COAL};

    public static final Quest[] QUESTBOARD_QUESTS = concatenateQuestArrays(D_QUESTS, C_QUEST);

    public static Quest[] concatenateQuestArrays(final Quest[] a, final Quest[] b) {
        final Quest[] c = new Quest[a.length + b.length];

        int index = 0;
        for (Quest quest : a) {
            c[index] = quest;
            index++;
        }

        for (Quest quest : b) {
            c[index] = quest;
            index++;
        }

        return c;
    }

    public static final Quest GRIMOIRE = new GrimoireQuest();
    public static final Quest REINFORCEMENT = new ManaReinforcementQuest();
    public static final Quest MANASKIN = new ManaSkinQuest();
    public static final Quest MANAZONEQUEST = new ManaZoneQuest();
    public static final Quest[] MAGICIAN = new Quest[] {GRIMOIRE, REINFORCEMENT, MANASKIN, MANAZONEQUEST};

    static
    {

        for (Quest quest : C_QUEST)
        {
            String resourceName = Beapi.getResourceName(quest.getId());
            langMap.put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

            for(Objective obj : quest.getObjectives())
            {
                langMap.put("quest.objective." + Main.MODID + "." + obj.getId(), obj.getTitle());
            }

            QUESTS.register(resourceName, () -> quest);
        }


        for (Quest quest : D_QUESTS)
        {
            String resourceName = Beapi.getResourceName(quest.getId());
            langMap.put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

            for(Objective obj : quest.getObjectives())
            {
                langMap.put("quest.objective." + Main.MODID + "." + obj.getId(), obj.getTitle());
            }

            QUESTS.register(resourceName, () -> quest);
        }

        for (Quest quest : MAGICIAN)
        {
            String resourceName = Beapi.getResourceName(quest.getId());
            langMap.put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

            for(Objective obj : quest.getObjectives())
            {
                langMap.put("quest.objective." + Main.MODID + "." + obj.getId(), obj.getTitle());
            }

            QUESTS.register(resourceName, () -> quest);

        }
    }
}
