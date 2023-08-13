package com.yuanno.block_clover.networking.client;

import java.util.Optional;
import java.util.function.Supplier;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class CUpdateQuestStatePacket
{
	private INBT data;
	private ResourceLocation questId;

	public CUpdateQuestStatePacket()
	{
	}

	public CUpdateQuestStatePacket(QuestId quest)
	{
		this.questId = quest.getRegistryName();
	}

	@Deprecated
	public CUpdateQuestStatePacket(IQuestData props)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeResourceLocation(this.questId);
	}

	public static CUpdateQuestStatePacket decode(PacketBuffer buffer)
	{
		CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
		msg.questId = buffer.readResourceLocation();
		return msg;
	}

	public static void handle(CUpdateQuestStatePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				if (message.questId == null)
					return;

				PlayerEntity player = ctx.get().getSender();
				// Searching if there's a nearby trainer, otherwise there's no reason for the player to accept or finish a quest


				IQuestData props = QuestDataCapability.get(player);
				QuestId questId = (QuestId) GameRegistry.findRegistry(QuestId.class).getValue(message.questId);

				if (questId == null || questId.isLocked(props))
					return;

				// Checking if the trainer we've found can indeed give the player the quest we're trying to accept/finish


				boolean updateClient = false;

				// If we're trying to accept the quest make sure we don't already have it in progress, otherwise if we're trying to finish one make sure we do have it in progress and its complete
				if (props.hasInProgressQuest(questId) && props.getInProgressQuest(questId).isComplete() && props.getInProgressQuest(questId).triggerCompleteEvent(player))
				{
					props.addFinishedQuest(questId);
					props.removeInProgressQuest(questId);
					updateClient = true;
				}
				else if (!props.hasInProgressQuest(questId))
				{
					Quest quest = questId.createQuest();
					if(quest.triggerStartEvent(player))
					{
						props.addInProgressQuest(quest);
						updateClient = true;
					}
				}

				if (updateClient)
				{
					PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
					PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), AbilityDataCapability.get(player)), player);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}

}