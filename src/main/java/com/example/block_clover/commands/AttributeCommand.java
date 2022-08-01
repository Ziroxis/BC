package com.example.block_clover.commands;

import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModValues;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncEntityStatsPacket;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class AttributeCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("attribute").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("ATTRIBUTE", StringArgumentType.string()).suggests(SUGGEST_SET)
                                        .executes((command) ->
                                        {

                                            String set = StringArgumentType.getString(command, "ATTRIBUTE");


                                            return setAttribute(command.getSource(), EntityArgument.getPlayer(command, "target"), set);
                                        }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("WIND");
        suggestions.add("FIRE");
        suggestions.add("LIGHT");
        suggestions.add("LIGHTNING");
        suggestions.add("DARKNESS");
        suggestions.add("EARTH");
        suggestions.add("SLASH");
        suggestions.add("ANTI-MAGIC");
        return ISuggestionProvider.suggest(suggestions.stream(),  builder);
    };

    private static int setAttribute(CommandSource command, PlayerEntity player, String set)
    {
        IEntityStats statsProps = EntityStatsCapability.get(player);
        switch (set)
        {
            case "WIND":
                statsProps.setAttribute(ModValues.WIND);
                break;
            case "FIRE":
                statsProps.setAttribute(ModValues.FIRE);
                break;
            case "LIGHT":
                statsProps.setAttribute(ModValues.LIGHT);
            case "LIGHTNING":
                statsProps.setAttribute(ModValues.LIGHTNING);
                break;
            case "DARKNESS":
                statsProps.setAttribute(ModValues.DARKNESS);
                break;
            case "EARTH":
                statsProps.setAttribute(ModValues.EARTH);
                break;
            case "SLASH":
                statsProps.setAttribute(ModValues.SLASH);
                break;
            case "ANTI-MAGIC":
                statsProps.setAttribute(ModValues.ANTIMAGIC);
                break;
            default:
                command.sendFailure(new TranslationTextComponent("Command didn't work"));

        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        command.sendSuccess(new TranslationTextComponent(player.getDisplayName().getString() + " " + "attribute set to " + set), true);

        return 1;
    }
}
