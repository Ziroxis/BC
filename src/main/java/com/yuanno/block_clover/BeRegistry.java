package com.yuanno.block_clover;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.init.ModRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;

public class BeRegistry {
    public static String registerName(String key, String localizedName) {
        BeRegistry.getLangMap().put(key, localizedName);
        return key;
    }
    private static HashMap<String, String> langMap = new HashMap<String, String>();
    public static final DeferredRegister<QuestId<?>> QUESTS = DeferredRegister.create(ModRegistries.QUESTS, Main.MODID);
    public static final DeferredRegister<ChallengeCore<?>> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, Main.MODID);

    public static <T extends Quest> QuestId<T> registerQuest(QuestId<T> quest)
    {
        String resourceName = BeJavapi.getResourceName(quest.getName());
        Beapi.getLangMap().put("quest." + Main.MODID + "." + resourceName, quest.getName());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<QuestId<?>> ret = RegistryObject.of(key, ModRegistries.QUESTS);
        if(!QUESTS.getEntries().contains(ret))
        {
            QUESTS.register(resourceName, () -> quest);
        }

        return quest;
    }
    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static final DeferredRegister<AbilityCore<?>> ABILITIES = DeferredRegister.create(ModRegistries.ABILITIES, Main.MODID);

    public static <T extends Ability> AbilityCore<T> registerAbility(AbilityCore<T> ability)
    {
        String resourceName = BeJavapi.getResourceName(ability.getName());
        BeRegistry.getLangMap().put("ability." + Main.MODID + "." + resourceName, ability.getName());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<AbilityCore<?>> ret = RegistryObject.of(key, ModRegistries.ABILITIES);
        if(!ABILITIES.getEntries().contains(ret))
        {
            ABILITIES.register(resourceName, () -> ability);
            ability.setIcon(key);
        }

        return ability;
    }
}
