package com.yuanno.block_clover.data.quest;

import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class QuestDataCapability
{

	@CapabilityInject(IQuestData.class)
	public static final Capability<IQuestData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>()
		{
			@Override
			public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				ListNBT questsInTracker = new ListNBT();
				for (int i = 0; i < instance.getInProgressQuests().length; i++)
				{
					Quest quest = instance.getInProgressQuests()[i];
					if(quest != null)
						questsInTracker.add(quest.save());
				}
				props.put("quests_in_tracker", questsInTracker);
				
				ListNBT finishedQuests = new ListNBT();
				for (int i = 0; i < instance.getFinishedQuests().size(); i++)
				{
					QuestId quest = instance.getFinishedQuests().get(i);
					CompoundNBT questNbt = new CompoundNBT();
					questNbt.putString("id", quest.getRegistryName().toString());
					finishedQuests.add(questNbt);
				}
				props.put("finished_quests", finishedQuests);
				
				return props;
			}
			
			@Override
			public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.clearInProgressQuests();
				instance.clearFinishedQuests();
				
				ListNBT trackerQuests = props.getList("quests_in_tracker", Constants.NBT.TAG_COMPOUND);
				for (int i = 0; i < trackerQuests.size(); i++)
				{
					try
					{
						CompoundNBT nbtQuests = trackerQuests.getCompound(i);
						QuestId questId = (QuestId) GameRegistry.findRegistry(QuestId.class).getValue(new ResourceLocation(nbtQuests.getString("id")));
						if(questId == null)
							continue;
						Quest quest = questId.createQuest();
						quest.load(nbtQuests);
						instance.setInProgressQuest(i, quest);
					}
					catch(Exception e)
					{
						continue;
					}
				}
				
				ListNBT finishedQuests = props.getList("finished_quests", Constants.NBT.TAG_COMPOUND);
				for (int i = 0; i < finishedQuests.size(); i++)
				{
					try
					{
						CompoundNBT nbtQuests = finishedQuests.getCompound(i);
						QuestId quest = (QuestId) GameRegistry.findRegistry(QuestId.class).getValue(new ResourceLocation(nbtQuests.getString("id")));
						instance.addFinishedQuest(quest);
					}
					catch(Exception e)
					{
						continue;
					}
				}
			}
			
		}, QuestDataBase::new);
	}
	
	public static IQuestData get(final PlayerEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
	}

	public static LazyOptional<IQuestData> getLazy(final PlayerEntity entity)
	{
		return entity.getCapability(INSTANCE, null);
	}
}
