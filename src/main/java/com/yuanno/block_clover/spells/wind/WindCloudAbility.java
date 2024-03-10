package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;

public class WindCloudAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Cloud", AbilityCategories.AbilityCategory.ATTRIBUTE, WindCloudAbility.class)
            .setDescription("Creates a cloud under you, making you able to fly")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    public int secondsActivated = 0;

    public WindCloudAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(15);
        this.setEvolutionCost(100);
        this.setmanaCost(5);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.abilities.mayfly = true;
        if(player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));
        return true;
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
}
