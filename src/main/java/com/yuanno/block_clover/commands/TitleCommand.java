package com.yuanno.block_clover.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TitleCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("title").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("TITLE", StringArgumentType.string())
                                .executes((command) ->
                                {

                                    String set = StringArgumentType.getString(command, "TITLE");


                                    return setAttribute(command.getSource(), EntityArgument.getPlayer(command, "target"), set);
                                }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        return ISuggestionProvider.suggest(suggestions.stream(),  builder);
    };

    private static int setAttribute(CommandSource command, PlayerEntity player, String set)
    {
        IEntityStats statsProps = EntityStatsCapability.get(player);
        statsProps.setTitle(set);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        command.sendSuccess(new TranslationTextComponent(player.getDisplayName().getString() + " " + "title set to " + set), true);

        return 1;
    }
}
