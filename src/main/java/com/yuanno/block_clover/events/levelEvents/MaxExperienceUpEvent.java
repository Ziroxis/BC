package com.yuanno.block_clover.events.levelEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class MaxExperienceUpEvent extends PlayerEvent
{
    public int maxExperience;

    public MaxExperienceUpEvent(PlayerEntity player, int maxExperience)
    {
        super(player);
        this.maxExperience = maxExperience;
    }
}
