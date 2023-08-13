package com.yuanno.block_clover.api.Quest;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.quest.IQuestData;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class QuestId<A extends Quest> extends ForgeRegistryEntry<QuestId<?>>
{
	private final String name;
	private Quest.IFactory<A> factory;
	private List<QuestId> requirements = new ArrayList<QuestId>();
	
	protected QuestId(String name, Quest.IFactory<A> factory)
	{
		this.name = name;
		this.factory = factory;
	}
	
	public List<QuestId> getRequirements()
	{
		return this.requirements;
	}
	
	private void setRequirements(List<QuestId> requirements)
	{
		this.requirements = requirements;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getLocalizedTitle() 
	{
		String id = Beapi.getResourceName(this.getName());
		return new TranslationTextComponent(String.format("quest." + Main.MODID + "." + id)).getString();
	}
	
	public boolean isLocked(IQuestData props)
	{
		List<QuestId> reqs = this.getRequirements();
		if(reqs == null || reqs.size() <= 0)
			return false;
		
		boolean isLocked = false;
		for(QuestId quest : reqs)
		{
			if(!props.hasFinishedQuest(quest))
			{
				isLocked = true;
				break;
			}
		}

		return isLocked;
	}
	
	public A createQuest()
	{
		return this.factory.create(this);
	}
	
	public static class Builder<T extends Builder, A extends Quest>
	{
		private final String name;
		private Quest.IFactory<A> factory;
		private List<QuestId> requirements = new ArrayList<QuestId>();

		public Builder(String name, Quest.IFactory<A> factory)
		{
			this.name = name;
			this.factory = factory;
		}

		public Builder<T, A> addRequirements(QuestId... requirements)
		{
			for(QuestId req : requirements)
				this.requirements.add(req);
			return this;
		}
		
		public QuestId<A> build()
		{
			QuestId<A> core = new QuestId(this.name, this.factory);	
			core.setRequirements(this.requirements);
			return core;
		}
	}
}
