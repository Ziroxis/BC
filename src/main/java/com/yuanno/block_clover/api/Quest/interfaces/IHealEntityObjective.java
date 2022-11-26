package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IHealEntityObjective
{
	boolean checkHeal(PlayerEntity player, LivingEntity target, float amount);
}
