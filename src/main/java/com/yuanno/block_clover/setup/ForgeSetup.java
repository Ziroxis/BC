package com.yuanno.block_clover.setup;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.commands.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        CommandDispatcher dispatcher = event.getServer().getCommands().getDispatcher();
        LiteralArgumentBuilder<CommandSource> masterBuilder = null;
        boolean masterCommandFlag = false;
        if(masterCommandFlag)
            masterBuilder = Commands.literal("mmnm");
        ExperienceCommand.register(dispatcher);
        AbilityCommand.register(dispatcher);
        LevelCommand.register(dispatcher);
        QuestCommand.register(dispatcher, masterBuilder);
        AttributeCommand.register(dispatcher);
        StructureCommand.register(dispatcher);
        RaceCommand.register(dispatcher);
    }
}
