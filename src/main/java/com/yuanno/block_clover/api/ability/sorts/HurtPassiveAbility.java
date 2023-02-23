package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.io.Serializable;

public class HurtPassiveAbility extends PassiveAbility
{
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHurt onHurtEvent = (player, attacker) -> { return true; };
	
	private float amount;

	public HurtPassiveAbility(AbilityCore core)
	{
		super(core);
	}
	
	public boolean hurt(LivingEntity entity, Entity source, float amount)
	{
		this.amount = amount;
		
		if(this.isPaused())
			return true;
		
		return this.onHurtEvent.onHurt(entity, source);
	}
	
	public float getAmount()
	{
		return this.amount;
	}
	
	/*
	 *	Interfaces
	 */
	public interface IOnHurt extends Serializable
	{
		boolean onHurt(LivingEntity entity, Entity source);
	}
}
