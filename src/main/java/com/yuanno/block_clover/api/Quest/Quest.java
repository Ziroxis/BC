package com.yuanno.block_clover.api.Quest;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.quest.IQuestData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Quest
{
	private QuestId core;
	
	private List<Objective> objectives = new ArrayList<Objective>();

	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IStarting onStartEvent = (player) -> { return true; };
	protected ICompleting onCompleteEvent = (player) -> { return true; };
	protected IShouldRestart shouldRestartEvent = (player) -> { return false; };
	private String description;
	private String rank;
	private Category category;
	public Quest(QuestId core)
	{
		this.core = core;
	}
	
	/*
	 *  Methods
	 */

	public QuestId getCore()
	{
		return this.core;
	}
	public String getRank() {return this.rank;}
	public void setRank(String rank) {this.rank = rank;}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@Override
	public boolean equals(Object quest)
	{
		if(quest == null || this.getCore() == null)
			return false;
		
		if(quest instanceof Quest)
		{
			if (((Quest) quest).getCore() == null)
				return false;
	
			if (this.getCore().equals(((Quest) quest).getCore()))
				return true;
		}
		else if(quest instanceof QuestId)
		{
			if (this.getCore().equals((quest)))
				return true;
		}
		
		return false;
	}

	public boolean removeQuestItem(PlayerEntity player, Item item, int amount)
	{
		int id = Beapi.getIndexOfItemStack(item, player.inventory);
		
		if(id < 0)
		{
			player.sendMessage(new TranslationTextComponent("Missing quest items: %s", new ItemStack(item).getHoverName().getString()), Util.NIL_UUID);
			return false;
		}
		
		player.inventory.getItem(id).shrink(amount);
		return true;
	}
	
	/*
	 *  Setters and Getters
	 */
	
	public boolean checkRestart(PlayerEntity player)
	{
		return this.shouldRestartEvent.check(player);
	}
	
	public boolean triggerCompleteEvent(PlayerEntity player)
	{
		return this.onCompleteEvent.check(player);
	}
	
	public boolean triggerStartEvent(PlayerEntity player)
	{
		return this.onStartEvent.check(player);
	}
	
	public void addObjectives(Objective... objectives)
	{
		for(Objective obj : objectives)
			this.addObjective(obj);
	}
	
	public void addObjective(Objective objective)
	{
		if(!this.objectives.contains(objective))
			this.objectives.add(objective);
	}
	
	public List<Objective> getObjectives()
	{
		return this.objectives;
	}
	
	public boolean isComplete()
	{
		return this.objectives.stream().allMatch(objective -> !objective.isOptional() && objective.isComplete());
	}
	
	public double getProgress()
	{
		int maxProgress = this.objectives.size();
		int completed = this.objectives.stream().filter(objective -> !objective.isOptional() && objective.isComplete()).collect(Collectors.toList()).size();

		double progress = completed / (double) maxProgress;
		
		return progress;
	}
	
	public void resetProgress()
	{
		this.objectives.stream().forEach((o) -> o.setProgress(0));
	}

	public boolean isLocked(IQuestData props)
	{
		return this.getCore().isLocked(props);
	}

	/*
	 *  Save / Load data
	 */
	
	public CompoundNBT save()
	{
		CompoundNBT nbt = new CompoundNBT();
		
		nbt.putString("id", this.core.getRegistryName().toString());
		ListNBT objectivesData = new ListNBT();
		for(Objective obj : this.getObjectives())
		{
			objectivesData.add(obj.save());
		}
		nbt.put("objectives", objectivesData);
		
		return nbt;
	}
	
	public void load(CompoundNBT nbt)
	{
		ListNBT objectivesData = nbt.getList("objectives", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < objectivesData.size(); i++)
		{
			CompoundNBT questData = objectivesData.getCompound(i);
			this.getObjectives().get(i).load(questData);
		}
	}


	/*
	 *  Interfaces
	 */
	public interface IFactory<A extends Quest>
	{
		A create(QuestId<A> questid);
	}
	
	public interface IShouldRestart extends Serializable
	{
		boolean check(PlayerEntity player);
	}
	
	public interface IStarting extends Serializable
	{
		boolean check(PlayerEntity player);
	}

	public interface ICompleting extends Serializable
	{
		boolean check(PlayerEntity player);
	}

	public Category getCategory()
	{
		return this.category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public enum Category
	{
		MAGICIAN, BOARD
	}
}
