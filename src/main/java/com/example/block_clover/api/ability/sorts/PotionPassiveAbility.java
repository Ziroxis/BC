package com.example.block_clover.api.ability.sorts;

import com.example.block_clover.api.ability.AbilityCategories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public abstract class PotionPassiveAbility extends PassiveAbility
{
	protected ICheckPotionEvent checkPotionEvent = (player, effect) -> { return true; };
	
	public PotionPassiveAbility(String name, AbilityCategories.AbilityCategory category)
	{
		super(name, category);
	}
	
	/** If the check returns false the potion effect given will not be applied, to change the properties of the effect use <code>ObfuscationReflectionHelper.setPrivateValue()</code> */
	public boolean check(PlayerEntity user, EffectInstance effect)
	{
		return this.checkPotionEvent.checkPotion(user, effect);
	}
	
	/*
	 *	Interfaces
	 */
	public interface ICheckPotionEvent
	{
		boolean checkPotion(PlayerEntity player, EffectInstance effect);
	}
}
