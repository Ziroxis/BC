package com.yuanno.block_clover.data.quest;



import com.yuanno.block_clover.quest.Objective;
import com.yuanno.block_clover.quest.Quest;

import java.util.List;

public interface IQuestData
{
	boolean addInProgressQuest(Quest quest);
	boolean setInProgressQuest(int slot, Quest quest);
	boolean removeInProgressQuest(Quest quest);
	boolean hasInProgressQuest(Quest quest);
	<T extends Quest> T getInProgressQuest(T quest);
	<T extends Quest> T getInProgressQuest(int slot);
	int getInProgressQuestSlot(Quest quest);
	List<Objective> getInProgressObjectives();
	Quest[] getInProgressQuests();
	void clearInProgressQuests(); 
	int countInProgressQuests();
	
	boolean addFinishedQuest(Quest quest);
	boolean removeFinishedQuest(Quest quest);
	boolean hasFinishedQuest(Quest quest);
	<T extends Quest> T getFinishedQuest(T quest);
	List<Quest> getFinishedQuests();
	void clearFinishedQuests(); 
	int countFinishedQuests();


}
