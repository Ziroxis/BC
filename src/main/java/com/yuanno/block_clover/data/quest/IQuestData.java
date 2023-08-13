package com.yuanno.block_clover.data.quest;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;

import java.util.List;

public interface IQuestData
{
	boolean addInProgressQuest(Quest quest);
	boolean setInProgressQuest(int slot, Quest quest);
	boolean removeInProgressQuest(QuestId quest);
	boolean removeInProgressQuest(Quest quest);
	boolean hasInProgressQuest(QuestId quest);
	boolean hasInProgressQuest(Quest quest);
	<T extends Quest> T getInProgressQuest(QuestId<T> quest);
	<T extends Quest> T getInProgressQuest(T quest);
	<T extends Quest> T getInProgressQuest(int slot);
	int getInProgressQuestSlot(Quest quest);
	<T extends Objective> List<T> getInProgressObjectives();
	Quest[] getInProgressQuests();
	void clearInProgressQuests(); 
	int countInProgressQuests();
	
	boolean addFinishedQuest(QuestId quest);
	boolean removeFinishedQuest(QuestId quest);
	boolean hasFinishedQuest(QuestId quest);
	<T extends QuestId> T getFinishedQuest(T quest);
	List<QuestId> getFinishedQuests();
	void clearFinishedQuests(); 
	int countFinishedQuests();
}
