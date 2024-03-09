package com.yuanno.block_clover.api.ability.interfaces;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;

public interface IEffectInstanceContinuousAbility {

    default void addEffectInstances(PlayerEntity player, Ability ability)
    {
        for (int i = 0; i < getEffectInstances().size(); i++)
        {
            if (!player.hasEffect(getEffectInstances().get(i).getEffect()))
                player.addEffect(getEffectInstances().get(i));
        }
    }

    default ArrayList<EffectInstance> getEffectInstances()
    {
        ArrayList<EffectInstance> effectInstances = new ArrayList<>();
        return effectInstances;
    }
}
