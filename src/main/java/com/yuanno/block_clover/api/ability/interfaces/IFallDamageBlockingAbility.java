package com.yuanno.block_clover.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;

public interface IFallDamageBlockingAbility
{
	public void resetFallDamage(LivingEntity entity);

	public boolean hasFallDamage();
}
