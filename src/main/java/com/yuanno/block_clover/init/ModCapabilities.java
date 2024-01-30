package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.AbilityDataProvider;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.ChallengesDataProvider;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.DevilProvider;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.EntityStatsProvider;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.data.quest.QuestDataProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModCapabilities {

    public static void init()
    {
        EntityStatsCapability.register();
        AbilityDataCapability.register();
        QuestDataCapability.register();
        DevilCapability.register();
        ChallengesDataCapability.register();
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() == null)
            return;

        if (event.getObject() instanceof LivingEntity)
        {
            event.addCapability(new ResourceLocation(Main.MODID, "entity_stats"), new EntityStatsProvider());
        }
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Main.MODID, "ability_data"), new AbilityDataProvider());
            event.addCapability(new ResourceLocation(Main.MODID, "quest_data"), new QuestDataProvider());
            event.addCapability(new ResourceLocation(Main.MODID, "devil_data"), new DevilProvider());
            event.addCapability(new ResourceLocation(Main.MODID, "challenges"), new ChallengesDataProvider());


        }
    }
}
