package com.yuanno.block_clover.api.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class AbilityUseEvent extends AbilityEvent {
    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        super(player, ability);
    }

    @Cancelable
    public static class Pre extends AbilityUseEvent
    {
        public Pre(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }

    public static class Post extends AbilityUseEvent
    {
        public Post(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }
}
