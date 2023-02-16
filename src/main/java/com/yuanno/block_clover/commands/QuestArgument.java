package com.yuanno.block_clover.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuanno.block_clover.api.Quest.Quest;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.CompletableFuture;

public class QuestArgument implements ArgumentType<Quest>
{
	@Override
	public Quest parse(StringReader reader) throws CommandSyntaxException
	{
		ResourceLocation resourcelocation = ResourceLocation.read(reader);
		Quest quest = GameRegistry.findRegistry(Quest.class).getValue(resourcelocation);
		return quest;
	}

	public static QuestArgument quest()
	{
		return new QuestArgument();
	}

	public static <S> Quest getQuest(CommandContext<S> context, String name)
	{
		return context.getArgument(name, Quest.class);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder)
	{
		StringReader stringreader = new StringReader(builder.getInput());
		stringreader.setCursor(builder.getStart());

		return this.suggestQuest(builder);
	}

	private CompletableFuture<Suggestions> suggestQuest(SuggestionsBuilder builder)
	{
		return ISuggestionProvider.suggestResource(GameRegistry.findRegistry(Quest.class).getKeys(), builder);
	}
}
