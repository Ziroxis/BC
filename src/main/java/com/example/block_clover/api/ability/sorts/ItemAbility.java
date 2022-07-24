package com.example.block_clover.api.ability.sorts;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.events.levelEvents.ExperienceUpEvent;
import com.example.block_clover.init.ModItems;
import com.example.block_clover.networking.ManaSync;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;

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
		this.duringContinuityEvent = this::duringContinuityEvent;
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
			/*
			IEntityStats playerStats = EntityStatsCapability.get(player);
			playerStats.alterExperience(getExperiencePoint());
			ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, getExperiencePoint());
			MinecraftForge.EVENT_BUS.post(eventExperience);
			playerStats.alterMana(getmanaCost());
			PacketHandler.sendTo(new ManaSync(playerStats.getMana()), player);
			PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), playerStats), player);

			 */

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

	private void duringContinuityEvent(PlayerEntity player, int timer)
	{
		/*
		if (this.isLoseMana() && player.tickCount % 20 == 0)
		{
			IEntityStats playerStats = EntityStatsCapability.get(player);
			playerStats.alterExperience(this.getPassiveExperience());
			playerStats.alterMana(-this.getPassiveManaCost());
			ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, getExperiencePoint());
			MinecraftForge.EVENT_BUS.post(eventExperience);
		}
		 */
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{

		for (int i = 0; i < 35; i++)
		{
			if (player.inventory.getItem(i).getItem().equals(ModItems.LIGHT_SWORD.get()))
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
