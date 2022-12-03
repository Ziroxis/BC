package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
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

import java.util.function.Supplier;

public class CUpdateQuestStatePacket
{
	private INBT data;
	private String questId;
	
	public CUpdateQuestStatePacket() {}

	public CUpdateQuestStatePacket(Quest quest)
	{
		this.questId = quest.getRegistryName().toString();
	}
	
	@Deprecated
	public CUpdateQuestStatePacket(IQuestData props)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		int len = this.questId.length();
		buffer.writeInt(len);
		buffer.writeUtf(this.questId, len);
	}

	public static CUpdateQuestStatePacket decode(PacketBuffer buffer)
	{
		CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
		int len = buffer.readInt();
		msg.questId = buffer.readUtf(len);
		return msg;
	}

	public static void handle(CUpdateQuestStatePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				if(Beapi.isNullOrEmpty(message.questId))
					return;
							
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				IQuestData props = QuestDataCapability.get(player);
				Quest quest = GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(message.questId));

				boolean updateClient = false;

				// If we're trying to accept the quest make sure we don't already have it in progress, otherwise if we're trying to finish one make sure we do have it in progress and its complete
				if(props.hasInProgressQuest(quest) && props.getInProgressQuest(quest).isComplete() && props.getInProgressQuest(quest).triggerCompleteEvent(player))
				{
					props.addFinishedQuest(quest);
					props.removeInProgressQuest(quest);
					updateClient = true;
				}
				else if(!props.hasInProgressQuest(quest) && quest.triggerStartEvent(player))
				{
					props.addInProgressQuest(quest);
					updateClient = true;
				}

				if(updateClient)
				{
					PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
					PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), AbilityDataCapability.get(player)), player);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
