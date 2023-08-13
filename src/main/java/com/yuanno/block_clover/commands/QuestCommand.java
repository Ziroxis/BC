package com.yuanno.block_clover.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncQuestDataPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("quest").requires(source -> source.hasPermission(2));

		builder
				.then(Commands.literal("finish")
						.then(Commands.argument("quest", QuestArgument.quest())
								.then(Commands.argument("target", EntityArgument.player())
										.executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))
								.executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), context.getSource().getPlayerOrException()))))
				.then(Commands.literal("give")
						.then(Commands.argument("quest", QuestArgument.quest())
								.then(Commands.argument("target", EntityArgument.player())
										.executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))
								.executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), context.getSource().getPlayerOrException()))))
				.then(Commands.literal("unfinish")
						.then(Commands.argument("quest", QuestArgument.quest())
								.then(Commands.argument("target", EntityArgument.player())
										.executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))
								.executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), context.getSource().getPlayerOrException()))))
				.then(Commands.literal("remove")
						.then(Commands.argument("quest", QuestArgument.quest())
								.then(Commands.argument("target", EntityArgument.player())
										.executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))
								.executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), context.getSource().getPlayerOrException()))));

		if(masterBuilder != null)
			masterBuilder.then(builder);
		else
			dispatcher.register(builder);
	}

	private static int unfinishQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		IAbilityData abilityData = AbilityDataCapability.get(player);

		if(props.hasFinishedQuest(quest))
		{
			props.removeFinishedQuest(quest);
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
		}
		else
			player.sendMessage(new StringTextComponent("You haven't finished this quest!"), Util.NIL_UUID);

		return 1;
	}

	private static int finishQuest(CommandContext<CommandSource> context, QuestId questId, ServerPlayerEntity player)
	{


		IQuestData props = QuestDataCapability.get(player);

		if(props.hasInProgressQuest(questId))
		{
			Quest quest = props.getInProgressQuest(questId);
			if(quest.triggerCompleteEvent(player))
			{
				props.addFinishedQuest(quest.getCore());
				props.removeInProgressQuest(quest);
				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			}
		}
		else if(!props.hasInProgressQuest(questId))
			player.sendMessage(new StringTextComponent("You don't have this quest!"), Util.NIL_UUID);

		return 1;
	}

	private static int giveQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);

		if(!props.hasInProgressQuest(quest))
		{
			props.addInProgressQuest(quest.createQuest());
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You aleady have this quest!"), Util.NIL_UUID);

		return 1;
	}

	private static int removeQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);

		if(props.hasInProgressQuest(quest))
		{
			props.removeInProgressQuest(quest);
			props.removeFinishedQuest(quest);
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You don't have this quest!"), Util.NIL_UUID);

		return 1;
	}
}
