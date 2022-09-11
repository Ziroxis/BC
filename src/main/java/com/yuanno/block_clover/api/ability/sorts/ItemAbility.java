package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class ItemAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	// TODO move the loseMana  and passive cost + experience to continuous abilities
	boolean loseMana = false;
	int passiveManaCost = 0;
	int passiveExperience = 0;
	public ItemAbility(String name, AbilityCategories.AbilityCategory category)
	{
		super(name, category);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	/*
	 *  Event Consumers
	 */
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.getItemInHand(player.getUsedItemHand()).isEmpty() && !this.getItemStack(player).isEmpty())
		{
			player.inventory.setItem(player.inventory.selected, this.getItemStack(player));
			return true;
		}
		else
		{
			if(this.getItemStack(player).isEmpty())
				player.sendMessage(new TranslationTextComponent("Cannot equip because it's an empty stack!"), Util.NIL_UUID);
			else
				player.sendMessage(new TranslationTextComponent("Cannot equip while holding another item in hand!"), Util.NIL_UUID);
			return false;
		}
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{

		for (int i = 0; i < 35; i++)
		{
			if (player.inventory.getItem(i).getItem().equals(this.getItemStack(player).getItem()))
				player.inventory.getItem(i).shrink(1);
		}



		return true;
	}
	/*
	 * 	Methods
	 */
	public abstract ItemStack getItemStack(PlayerEntity player);
	public abstract boolean canBeActive(PlayerEntity player);

	public void setPassiveExperience(int passiveExperience)
	{
		this.passiveExperience = passiveExperience;
	}

	public int getPassiveExperience()
	{
		return this.passiveExperience;
	}

	public void setPassiveManaCost(int passiveManaCost)
	{
		this.passiveManaCost = passiveManaCost;
	}

	public int getPassiveManaCost()
	{
		return this.passiveManaCost;
	}

	public void loseMana(boolean flag)
	{
		this.loseMana = flag;
	}

	public boolean isLoseMana()
	{
		return this.loseMana;
	}
}
