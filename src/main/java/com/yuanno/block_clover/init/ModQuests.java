package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestRegistry;
import com.yuanno.block_clover.quests.*;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModQuests {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(QuestRegistry.QUESTS, Main.MODID);

    public static final Quest KILL_CREEPER = new KillCreeperQuest();
    public static final Quest[] D_QUESTS = new Quest[] {KILL_CREEPER};

    public static final Quest GRIMOIRE = new GrimoireQuest();
    public static final Quest REINFORCEMENT = new ManaReinforcementQuest();
    public static final Quest MANASKIN = new ManaSkinQuest();
    public static final Quest MANAZONEQUEST = new ManaZoneQuest();
    public static final Quest[] MAGICIAN = new Quest[] {GRIMOIRE, REINFORCEMENT, MANASKIN, MANAZONEQUEST};

    static
    {
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
