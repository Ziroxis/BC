package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;

public class EarthManipulationAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Earth manipulation", AbilityCategories.AbilityCategory.ATTRIBUTE, EarthManipulationAbility.class)
            .setDescription("Manipulates the earth giving you the ability to fly")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    public int secondsActivated = 0;

    public EarthManipulationAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(15);
        this.setEvolutionCost(100);
        this.setmanaCost(5);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.abilities.mayfly = true;
        if(player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));
        return true;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    { /*
        if (player.tickCount % 20 == 0)
            secondsActivated += 1;
        /*
        if (secondsActivated >= 120)
            this.stopContinuity(player);

         */
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        if (!player.isCreative())
        {
            player.abilities.mayfly = false;
            player.abilities.flying = false;
            player.fallDistance = 0;
        }        if(player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));
    return true;
    }

    private boolean onStopContinuityEvent(PlayerEntity player)
    {
        if (!player.isCreative())
        {
            player.abilities.mayfly = false;
            player.abilities.flying = false;
            player.fallDistance = 0;
        }        if(player instanceof ServerPlayerEntity)
        ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));
        return true;
    }
}
