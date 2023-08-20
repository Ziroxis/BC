package com.yuanno.block_clover.data.quest;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.init.ModValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestDataBase implements IQuestData
{
	private Quest[] inProgressQuests = new Quest[ModValues.MAX_IN_PROGRESS_QUESTS];
	private List<QuestId> finishedQuests = new ArrayList<QuestId>();

	@Override
	public void fullReset() {
		Arrays.fill(inProgressQuests, null);
		finishedQuests.clear();
	}


	@Override
	public boolean addInProgressQuest(Quest quest)
	{
		for(int i = 0; i < this.inProgressQuests.length; i++)
		{
			Quest ogQuest = this.inProgressQuests[i];
			if(ogQuest == null)
			{
				 this.inProgressQuests[i] = quest;
				 return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean setInProgressQuest(int slot, Quest quest)
	{
		Quest ogQuest = this.getInProgressQuest(quest);
		if (ogQuest == null && slot <= ModValues.MAX_IN_PROGRESS_QUESTS)
		{
			this.inProgressQuests[slot] = quest;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeInProgressQuest(QuestId quest)
	{
		Quest ogQuest = this.getInProgressQuest(quest);
		if (ogQuest != null)
		{		
			for(int i = 0; i < this.inProgressQuests.length; i++)
			{
				Quest inProgressQuest = this.inProgressQuests[i];
				if(inProgressQuest != null && inProgressQuest.equals(ogQuest))
				{
					inProgressQuest.resetProgress();
					this.inProgressQuests[i] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean removeInProgressQuest(Quest quest)
	{
		Quest ogQuest = this.getInProgressQuest(quest);
		if (ogQuest != null)
		{		
			for(int i = 0; i < this.inProgressQuests.length; i++)
			{
				Quest inProgressQuest = this.inProgressQuests[i];
				if(inProgressQuest != null && inProgressQuest.equals(ogQuest))
				{
					inProgressQuest.resetProgress();
					this.inProgressQuests[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasInProgressQuest(QuestId quest)
	{
		return Arrays.stream(this.inProgressQuests)
			.filter(qst -> qst != null)
			.anyMatch(qst -> qst.getCore().equals(quest));
	}
	
	@Override
	public boolean hasInProgressQuest(Quest quest)
	{
		return Arrays.stream(this.inProgressQuests)
			.filter(qst -> qst != null)
			.anyMatch(qst -> qst.equals(quest));
	}

	@Override
	public <T extends Quest> T getInProgressQuest(QuestId<T> quest)
	{
		return (T) Arrays.stream(this.inProgressQuests)
			.filter(qst -> qst != null && qst.getCore().equals(quest))
			.findFirst().orElse(null);
	}
	
	@Override
	public <T extends Quest> T getInProgressQuest(T quest)
	{
		return (T) Arrays.stream(this.inProgressQuests)
			.filter(qst -> qst != null && qst.equals(quest))
			.findFirst().orElse(null);
	}

	@Override
	public <T extends Quest> T getInProgressQuest(int slot)
	{
		return (T) this.inProgressQuests[slot];
	}
	
	@Override
	public int getInProgressQuestSlot(Quest quest)
	{
		for(int i = 0; i < this.inProgressQuests.length; i++)
		{
			if(this.inProgressQuests[i] != null && this.inProgressQuests[i].equals(quest))
				return i;
		}
		
		return -1;
	}
	
	@Override
	public <T extends Objective> List<T> getInProgressObjectives()
	{
		return (List<T>) Arrays.stream(this.getInProgressQuests())
			.filter(q -> q != null && !q.isComplete())
			.flatMap(q -> q.getObjectives().stream())
			.filter(o -> !o.isHidden() && !o.isLocked())
			.collect(Collectors.toList());
	}
	
	@Override
	public Quest[] getInProgressQuests()
	{
		return this.inProgressQuests;
	}

	@Override
	public void clearInProgressQuests()
	{
		for(int i = 0; i < this.inProgressQuests.length; i++)
		{
			Quest quest = this.inProgressQuests[i];
			if(quest != null)
			{
				this.inProgressQuests[i] = null;
			}
		}	
	}

	@Override
	public int countInProgressQuests()
	{
		return Arrays.stream(this.inProgressQuests)
			.filter(quest -> quest != null)
			.mapToInt(q -> 1).sum();
	}

	@Override
	public boolean addFinishedQuest(QuestId quest)
	{
		QuestId ogQuest = this.getFinishedQuest(quest);
		if (ogQuest == null)
		{
			this.finishedQuests.add(quest);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFinishedQuest(QuestId quest)
	{
		QuestId ogQuest = this.getFinishedQuest(quest);
		if (ogQuest != null)
		{
			this.finishedQuests.remove(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasFinishedQuest(QuestId quest)
	{
		return this.finishedQuests.stream().filter(q -> q != null).anyMatch(qst -> qst.equals(quest));
	}

	@Override
	public <T extends QuestId> T getFinishedQuest(T quest)
	{
		return (T) this.finishedQuests.stream().filter(qst -> qst != null && qst.equals(quest)).findFirst().orElse(null);
	}

	@Override
	public List<QuestId> getFinishedQuests()
	{
		return this.finishedQuests.stream().filter(q -> q != null).collect(Collectors.toList());
	}

	@Override
	public void clearFinishedQuests()
	{
		this.finishedQuests.clear();
	}

	@Override
	public int countFinishedQuests()
	{
		return this.finishedQuests.size();
	}
}
