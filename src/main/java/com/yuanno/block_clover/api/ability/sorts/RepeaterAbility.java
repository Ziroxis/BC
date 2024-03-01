package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.events.ability.AbilityUseEvent;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

/**
 * Does something x amount of times with y interval, amount and time set in {@link #setMaxRepeaterCount(int, int)}.
 * The something that actually happens is in {@link #use(PlayerEntity)}
 * The repeating of the thing is in {@link #tick(PlayerEntity)}
 * Ability does have an end so the repeating stops {@link #endContinuity(PlayerEntity)}
 */
public abstract class RepeaterAbility extends ContinuousAbility implements IParallelContinuousAbility {


    private int repeaterCount;
    private int maxRepeaterCount;
    private int repeaterInterval;

    public RepeaterAbility(AbilityCore core)
    {
        super(core);
    }

    /*
     * 	Event Starters
     */
    private void repeater(PlayerEntity player, int passiveTimer)
    {
        if(this.repeaterCount > 0 && passiveTimer % this.repeaterInterval == 0)
        {
            if(this.onUseEvent.onUse(player))
                this.repeaterCount--;
        }
    }

    /*
     * 	Setters/Getters
     */
    public void setMaxRepeaterCount(int count, int interval)
    {
        this.maxRepeaterCount = count;
        this.repeaterCount = this.maxRepeaterCount;
        this.repeaterInterval = interval;
        int threshold = (int) Math.ceil((this.repeaterCount * this.repeaterInterval) / 20.0f);
        if(this.repeaterInterval == 0)
            threshold = -1;
        this.setThreshold(threshold);
    }

    public void setRepeaterCount(int count)
    {
        this.repeaterCount = count;
    }


    public int getMaxRepeaterCount()
    {
        return this.maxRepeaterCount;
    }

    public int getRepeaterCount(){
        return this.repeaterCount;
    }

    public int getRepeaterInterval()
    {
        return this.repeaterInterval;
    }
    /*
     * 	Methods
     */
    @Override
    public void use(PlayerEntity player)
    {
        super.use(player);
        AbilityUseEvent pre = new AbilityUseEvent.Pre(player, this);
        MinecraftForge.EVENT_BUS.post(pre);

    }

    @Override
    public void tick(PlayerEntity player)
    {
        if(this.isContinuous())
        {
            if(ExtendedWorldData.get(player.level).isInsideRestrictedArea((int)player.getX(), (int)player.getY(), (int)player.getZ()))
            {
                this.endContinuity(player);
                return;
            }

            this.continueTime++;
            if(!player.level.isClientSide) {
                this.duringContinuityEvent.duringContinuity(player, this.continueTime);
                this.repeater(player, this.continueTime);
            }

            if(this.getThreshold() != 0 && this.continueTime >= this.getThreshold())
                this.endContinuity(player);
        }
    }

    @Override
    public void endContinuity(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if(this.onEndContinuityEvent.onEndContinuity(player))
        {
            this.continueTime = 0;
            this.repeaterCount = this.maxRepeaterCount;
            this.startCooldown(player);
            AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
            MinecraftForge.EVENT_BUS.post(post);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
    }

}
