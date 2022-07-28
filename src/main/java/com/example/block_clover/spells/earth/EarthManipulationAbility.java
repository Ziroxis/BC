package com.example.block_clover.spells.earth;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;

public class EarthManipulationAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final EarthManipulationAbility INSTANCE = new EarthManipulationAbility();

    public EarthManipulationAbility()
    {
        super("Earth Manipulation", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Manipulates the earth giving you the ability to fly");
        this.setMaxCooldown(15);
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
