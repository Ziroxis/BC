package com.yuanno.block_clover.api.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class AbilityEvolutionEvent extends AbilityEvent {
    public AbilityEvolutionEvent(PlayerEntity player, Ability ability)
    {
        super(player, ability);
    }
}
