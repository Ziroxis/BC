package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestRegistry;
import com.yuanno.block_clover.quests.ManaSkinQuest;
import com.yuanno.block_clover.quests.ManaZoneQuest;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModQuests {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(QuestRegistry.QUESTS, Main.MODID);

    public static final Quest MANASKIN = new ManaSkinQuest();
    public static final Quest MANAZONEQUEST = new ManaZoneQuest();
    public static final Quest[] MANA = new Quest[] {MANASKIN, MANAZONEQUEST};

    static
    {
        for (Quest quest : MANA)
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
