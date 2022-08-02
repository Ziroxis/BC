package com.yuanno.block_clover.events.levelEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExperienceUpEvent extends PlayerEvent
{
    public int experience;

    public ExperienceUpEvent(PlayerEntity player, int experience) {
        super(player);
        this.experience = experience;
    }
}
