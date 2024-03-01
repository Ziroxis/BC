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
 * Ability class that handles the abilities that charge up and then something happen
 * Ability starts: {@link #use(PlayerEntity)}
 * Ability charges up: {@link #charging(PlayerEntity)}
 * Ability does something after charge (can choose when to end charge or no): {@link #stopCharging(PlayerEntity)}
 */
public class ChargeableAbility extends Ability {

    private int chargeTime;
    private int maxChargeTime;
    private boolean isCancelable;

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IOnStartCharging onStartChargingEvent = (player) -> { return true; };
    protected IOnEndCharging onEndChargingEvent = (player) -> { return true; };
    protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {};

    public ChargeableAbility(AbilityCore core)
    {
        super(core);
    }

    /**
     * Happens when the player uses this spell, first instance of it happens here
     */
    @Override
    public void use(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (!this.canUse(player))
            return;
        if(this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);
        if(this.isCharging() && this.chargeTime > 0)
            this.stopCharging(player);
        else if(this.isOnStandby())
        {
            if(this.onStartChargingEvent.onStartCharging(player))
            {
                this.checkAbilityPool(player, State.CHARGING);
                AbilityUseEvent pre = new AbilityUseEvent.Pre(player, this);
                MinecraftForge.EVENT_BUS.post(pre);

                this.chargeTime = this.maxChargeTime;
                this.startCharging(player);
                PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
            }
        }
    }

    /*
     *  Setters / Getters
     */
    public void setMaxChargeTime(double seconds)
    {
        this.maxChargeTime = (int) (seconds * 20);
        this.chargeTime = this.maxChargeTime;
    }

    public int getMaxChargeTime()
    {
        return this.maxChargeTime;
    }

    public int getChargeTime()
    {
        return this.chargeTime;
    }

    public void setChargeTime(int time)
    {
        this.chargeTime = time * 20;
    }

    public void setCancelable()
    {
        this.isCancelable = true;
    }

    public boolean isCancelable()
    {
        return this.isCancelable;
    }


    /**
     * Called every tick after the player has done #use
     * called server and client side
     */
    public void charging(PlayerEntity player)
    {
        if(!this.canUse(player))
        {
            this.stopCharging(player);
            return;
        }

        player.level.getProfiler().push(BeJavapi.getResourceName(this.getName()));

        {
            if (this.isCharging() && this.chargeTime > 0) {
                this.chargeTime -= 1 * this.getTimeProgression();
                if (!player.level.isClientSide && !this.isStateForced())
                    this.duringChargingEvent.duringCharging(player, this.chargeTime);
            } else if (this.isCharging() && this.chargeTime <= 0)
                this.stopCharging(player);
        }


        player.level.getProfiler().pop();
    }

    public void startCharging(PlayerEntity player)
    {
        this.setState(State.CHARGING);
    }

    /**
     * Called when the player ends the charging, can also happen by cancelling yourself
     *
     */
    public void stopCharging(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (!this.isStateForced() && this.onEndChargingEvent.onEndCharging(player))
        {
            this.checkAbilityPool(player, State.COOLDOWN);
            this.setForcedState(false);

            AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
            MinecraftForge.EVENT_BUS.post(post);
            this.chargeTime = this.maxChargeTime;
            this.startCooldown(player);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
    }

    /*
     *	Interfaces
     */
    public interface IDuringCharging extends Serializable
    {
        void duringCharging(PlayerEntity player, int chargeTime);
    }

    public interface IOnStartCharging extends Serializable
    {
        boolean onStartCharging(PlayerEntity player);
    }

    public interface IOnEndCharging extends Serializable
    {
        boolean onEndCharging(PlayerEntity player);
    }
}
