package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class AbilityEvent extends PlayerEvent {

    private Ability ability;

    public AbilityEvent(PlayerEntity player, Ability ability)
    {
        super(player);
        this.ability = ability;
    }

    public Ability getAbility()
    {
        return this.ability;
    }
}
