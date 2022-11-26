package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

public class PassiveAbility extends Ability {

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IDuringPassive duringPassiveEvent = (player) -> { };


    private boolean isPaused = false;

    public PassiveAbility(String name, AbilityCategories.AbilityCategory category)
    {
        super(name, category);
        this.hideInGUI(true);
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
