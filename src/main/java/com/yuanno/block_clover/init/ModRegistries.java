package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModRegistries {

    static
    {
        make(new ResourceLocation(Main.MODID, "abilities_bc"), AbilityCore.class);
        make(new ResourceLocation(Main.MODID, "quests_bc"), QuestId.class);
        make(new ResourceLocation(Main.MODID, "challenges"), ChallengeCore.class);

    }
    public static final IForgeRegistry<AbilityCore<?>> ABILITIES = RegistryManager.ACTIVE.getRegistry(AbilityCore.class);
    public static final IForgeRegistry<QuestId<?>> QUESTS = RegistryManager.ACTIVE.getRegistry(QuestId.class);
    public static final IForgeRegistry<ChallengeCore<?>> CHALLENGES = RegistryManager.ACTIVE.getRegistry(ChallengeCore.class);

    public static <T extends IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type)
    {
        new RegistryBuilder<T>().setName(name).setType(type).setMaxID(Integer.MAX_VALUE - 1).create();
    }
}
