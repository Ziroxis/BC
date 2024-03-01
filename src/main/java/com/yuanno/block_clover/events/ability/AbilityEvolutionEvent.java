package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Server side event, happens when a spell is evolved
 */
@Cancelable
public class AbilityEvolutionEvent extends AbilityEvent {
    public AbilityEvolutionEvent(PlayerEntity player, Ability ability)
    {
        super(player, ability);
    }
}
