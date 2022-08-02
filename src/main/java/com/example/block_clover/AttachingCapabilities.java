package com.example.block_clover;

import com.example.block_clover.data.ability.AbilityDataProvider;
import com.example.block_clover.data.quest.QuestDataProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AttachingCapabilities {
    public static class Registry
    {
        @SubscribeEvent
        public void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
        {
            if (event.getObject() instanceof PlayerEntity)
            {
                event.addCapability(new ResourceLocation(Main.MODID, "ability_data"), new AbilityDataProvider());
                event.addCapability(new ResourceLocation(Main.MODID, "quest_data"), new QuestDataProvider());
            }
        }
    }
}
