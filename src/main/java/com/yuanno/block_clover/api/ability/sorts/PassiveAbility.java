package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

/**
 * Quite different from {@link ContinuousAbility}, this does not need to be equipped and runs every tick except if paused, so the spell doesn't start or end.
 * It does something or it does not do something.
 * Checks if it can be used {@link #canUse(PlayerEntity)} and then runs the {@link #duringPassiveEvent} every tick.
 */
public class PassiveAbility extends Ability {

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IDuringPassive duringPassiveEvent = (player) -> { };


    private boolean isPaused = false;

    public PassiveAbility(AbilityCore core)
    {
        super(core);
        //this.hideInGUI(true);
    }

    public void setPause(boolean flag)
    {
        this.isPaused = flag;
    }

    public boolean isPaused()
    {
        return this.isPaused;
    }

    @Override
    public void use(PlayerEntity player) {}


    public void tick(PlayerEntity player)
    {
        if(!this.canUse(player))
            return;

        this.duringPassiveEvent.duringPassive(player);

        if(this.isOnCooldown())
            this.cooldown(player);
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {

        if(this.isPaused)
            return false;

        return super.canUse(player);
    }

    public interface IDuringPassive extends Serializable
    {
        void duringPassive(PlayerEntity player);
    }
}
