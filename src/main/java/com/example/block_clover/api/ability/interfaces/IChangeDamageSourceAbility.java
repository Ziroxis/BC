package com.example.block_clover.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IChangeDamageSourceAbility
{
	/*
	 * 	Methods
	 */
	float damageToEntityWithSource(PlayerEntity player, LivingEntity entity);
	
	DamageSource getSourceToUse(PlayerEntity player);
	
	boolean cancelsOriginalDamage();
	
	boolean isSourceChangeEnabled();
}