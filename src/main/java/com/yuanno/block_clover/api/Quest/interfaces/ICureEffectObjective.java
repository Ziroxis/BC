package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public interface ICureEffectObjective
{
	boolean checkEffect(PlayerEntity player, EffectInstance effectInstance);
}
