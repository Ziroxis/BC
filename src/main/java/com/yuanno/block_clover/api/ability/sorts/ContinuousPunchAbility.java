package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

/**
 * Specific class made for continuous enhanced punches with effect
 * todo need to fix the issue nothing happens when hitting an entity
 */
public abstract class ContinuousPunchAbility extends ContinuousAbility
{
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHitEntity onHitEntityEvent = (player, target) -> { return 0; };
	
	public ContinuousPunchAbility(AbilityCore core)
	{
		super(core);
	}
	
	
	/*
	 *  Methods 
	 */
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{


		//this.stopContinuity(player);
		return this.onHitEntityEvent.onHitEntity(player, target);
	}
	
	/*
	 *	Interfaces
	 */
	public interface IOnHitEntity extends Serializable
	{
		float onHitEntity(PlayerEntity player, LivingEntity target);
	}
}
