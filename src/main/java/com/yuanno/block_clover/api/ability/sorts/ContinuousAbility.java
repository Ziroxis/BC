package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.events.ability.AbilityUseEvent;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.experience.ExperienceUpEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

import java.io.Serializable;

/**
 * Class made for abilities that are active (for a time)
 * Ability starts: {@link #use(PlayerEntity)}
 * Something happens every tick while ability active: {@link #tick(PlayerEntity)}
 * Ability ends: {@link #endContinuity(PlayerEntity)}
 */
public abstract class ContinuousAbility extends Ability {

    private int threshold = 0;
    protected int continueTime = 0;

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IOnStartContinuity onStartContinuityEvent = (player) -> { return true; };
    protected IOnEndContinuity onEndContinuityEvent = (player) -> { return true; };
    protected IDuringContinuity duringContinuityEvent = (player, continuousTime) -> {};

    protected IOnStopContinuity onStopContinuityEvent = (player) -> {};


    public ContinuousAbility(AbilityCore core)
    {
        super(core);
    }


    /**
     * Happens when pressing the spell for the first time
     */
    @Override
    public void use(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);

        if(!this.isContinuous())
        {
            if(!this.isOnStandby())
                return;

            if (this.onStartContinuityEvent.onStartContinuity(player))
            {
                this.checkAbilityPool(player, State.CONTINUOUS);

                this.startContinuity(player);
                PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
            }
        }
        else
        {
            if (!this.isStateForced()) {
                this.endContinuity(player);
            }
        }
    }



    /*
     * 	Setters/Getters
     */
    public void setThreshold(int threshold)
    {
        this.threshold = threshold * 20;
    }

    public void setThresholdInTicks(int threshold)
    {
        this.threshold = threshold;
    }

    public int getThreshold()
    {
        return this.threshold;
    }

    public void setContinueTime(int time)
    {
        this.continueTime = time * 20;;
    }

    public int getContinueTime()
    {
        return this.continueTime;
    }



    /**
     * here is what happens every tick for the continuous ability/spells
     */
    public void tick(PlayerEntity player) {
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        if (player.level.isClientSide)
            return;

        if (!canUse(player)) {
            endContinuity(player);
            return;
        }

        String resourceName = BeJavapi.getResourceName(getName());
        player.level.getProfiler().push(resourceName);

        if (isContinuous()) {
            continueTime++;

            boolean shouldEndContinuity =
                    (threshold > 0 && continueTime >= threshold) || (propsEntity.getMana() < getmanaCost() + 5 && getmanaCost() != 0); // checks if the ability should continue

            if (!isStateForced()) {
                duringContinuityEvent.duringContinuity(player, continueTime);
            }

            if (shouldEndContinuity) {
                endContinuity(player);
            }

            AbilityUseEvent per = new AbilityUseEvent.Per(player, this);
            MinecraftForge.EVENT_BUS.post(per);
        }

        player.level.getProfiler().pop();
    }


    public void startContinuity(PlayerEntity player)
    {
        this.setState(State.CONTINUOUS);
    }
    public void endContinuity(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if(this.onEndContinuityEvent.onEndContinuity(player))
        {
            this.checkAbilityPool(player, State.COOLDOWN);
            this.continueTime = 0;
            this.startCooldown(player);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
            this.onStopContinuityEvent.onStopContinuity(player);
        }
    }

    /*
     *	Interfaces
     */
    public interface IDuringContinuity extends Serializable
    {
        void duringContinuity(PlayerEntity player, int passiveTime);
    }

    public interface IOnStartContinuity extends Serializable
    {
        boolean onStartContinuity(PlayerEntity player);
    }

    public interface IOnEndContinuity extends Serializable
    {
        boolean onEndContinuity(PlayerEntity player);
    }
    public interface IOnStopContinuity extends Serializable
    {
        void onStopContinuity(PlayerEntity player);
    }
}
