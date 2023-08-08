package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityUseEvent;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

import java.io.Serializable;

/**
 * Class made for abilities that are active (for a time)
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


    /*
     *  Event Starters
     */
    @Override
    public void use(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);

        AbilityUseEvent event = new AbilityUseEvent(player, this);
        if (MinecraftForge.EVENT_BUS.post(event))
            return;

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
            if (!this.isStateForced())
                this.endContinuity(player);

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



    /*
     * 	Methods
     */
    public void tick(PlayerEntity player)
    {
        /*
        if(player.level.isClientSide)
        	return;
        */

        IEntityStats propsEntity = EntityStatsCapability.get(player);
        if(!this.canUse(player))
        {
            this.stopContinuity(player);
            return;
        }

        player.level.getProfiler().push(Beapi.getResourceName(this.getName()));

        if (this.isContinuous()) {
            this.continueTime++;

            boolean isClientOrServer = this.isClientSide() || !player.level.isClientSide;
            boolean shouldEndContinuity = this.threshold > 0 && this.continueTime >= this.threshold
                    || propsEntity.getMana() < getmanaCost() + 10 && getmanaCost() != 0;

            if (isClientOrServer && !this.isStateForced()) {
                this.duringContinuityEvent.duringContinuity(player, this.continueTime);
            }

            if (shouldEndContinuity) {
                this.endContinuity(player);
            }

            if (player.tickCount % 20 == 0) {
                int experiencePoint = getExperiencePoint();
                int manaCost = (int) getmanaCost();

                if (propsEntity.getLevel() < getExperienceGainLevelCap()) {
                    propsEntity.alterExperience(experiencePoint);
                    ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, experiencePoint);
                    MinecraftForge.EVENT_BUS.post(eventExperience);
                }

                int currentMana = (int) propsEntity.getMana();
                if (currentMana > manaCost) {
                    propsEntity.alterMana(-manaCost);
                }
            }

            int currentMana = (int) propsEntity.getMana();
            PacketHandler.sendTo(new ManaSync(currentMana), player);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
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
            this.stopContinuity(player);
        }
    }
    public void stopContinuity(PlayerEntity player)
    {
        this.checkAbilityPool(player, State.COOLDOWN);
        this.continueTime = 0;
        this.startCooldown(player);
        if (!player.level.isClientSide)
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        this.onStopContinuityEvent.onStopContinuity(player);
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
