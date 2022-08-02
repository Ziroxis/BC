package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.EntityStatsProvider;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> e)
    {
        if (e.getObject() == null)
            return;

        if (e.getObject() instanceof LivingEntity)
        {
            e.addCapability(new ResourceLocation(Main.MODID, "entity_stats"), new EntityStatsProvider());
        }
    }
}
