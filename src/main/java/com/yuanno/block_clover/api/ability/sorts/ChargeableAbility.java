package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
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

public class ChargeableAbility extends Ability {

    private int chargeTime;
    private int maxChargeTime;
    private boolean isCancelable;

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IOnStartCharging onStartChargingEvent = (player) -> { return true; };
    protected IOnEndCharging onEndChargingEvent = (player) -> { return true; };
    protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {};

    public ChargeableAbility(String name, AbilityCategories.AbilityCategory category)
    {
        super(name, category);
    }

    /*
     *  Event Starters
     */
    @Override
    public void use(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        if(this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);

        AbilityUseEvent event = new AbilityUseEvent(player, this);
        if (MinecraftForge.EVENT_BUS.post(event))
            return;

        if(this.isCharging() && this.chargeTime > 0)
            this.stopCharging(player);
        else if(this.isOnStandby())
        {
            if(this.onStartChargingEvent.onStartCharging(player))
            {
                if (propsEntity.getLevel() < getExperienceGainLevelCap())
                {
                    propsEntity.alterExperience(getExperiencePoint());
                    ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, getExperiencePoint());
                    MinecraftForge.EVENT_BUS.post(eventExperience);
                }
                propsEntity.alterMana((int) - getmanaCost());
                PacketHandler.sendTo(new ManaSync(propsEntity.getMana()), player);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);

                this.checkAbilityPool(player, State.CHARGING);



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



    /*
     *  Methods
     */
    public void charging(PlayerEntity player)
    {
        //if(player.level.isClientSide)
        //	return;

        if(!this.canUse(player))
        {
            this.stopCharging(player);
            return;
        }

        player.level.getProfiler().push(Beapi.getResourceName(this.getName()));

        if(this.isCharging() && this.chargeTime > 0)
        {
            this.chargeTime -= 1 * this.getTimeProgression();
            if(!player.level.isClientSide && !this.isStateForced())
                this.duringChargingEvent.duringCharging(player, this.chargeTime);
        }
        else if(this.isCharging() && this.chargeTime <= 0)
        {
            this.endCharging(player);
        }

        player.level.getProfiler().pop();
    }

    public void startCharging(PlayerEntity player)
    {
        this.setState(State.CHARGING);
    }

    public void stopCharging(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (!this.isStateForced() && this.onEndChargingEvent.onEndCharging(player))
        {
            this.checkAbilityPool(player, State.COOLDOWN);
            this.setForcedState(false);


            this.chargeTime = this.maxChargeTime;
            this.startCooldown(player);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
    }

    public void endCharging(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if (!this.isStateForced() && this.onEndChargingEvent.onEndCharging(player))
        {
            this.stopCharging(player);
        }
    }

    public String[] getChargedShouts()
    {
        String[] nameSplit = this.getDisplayName().split(" ");
        int midPoint = (int) Math.ceil(nameSplit.length / 2.0D);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < midPoint; i++)
        {
            sb.append(nameSplit[i] + " ");
        }
        String firstShout = sb.toString().replaceAll("[:-]", "");
        sb = new StringBuilder();
        for(int i = midPoint; i < nameSplit.length; i++)
        {
            sb.append(nameSplit[i] + " ");
        }
        String secondShout = sb.toString().replaceAll("[:-]", "");

        return secondShout.length() > 0 ? new String[]{firstShout, secondShout} : new String[]{firstShout};
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
