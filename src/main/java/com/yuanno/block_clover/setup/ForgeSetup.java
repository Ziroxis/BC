package com.yuanno.block_clover.setup;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.commands.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        CommandDispatcher dispatcher = event.getServer().getCommands().getDispatcher();

        TitleCommand.register(dispatcher);
        ExperienceCommand.register(dispatcher);
        AbilityCommand.register(dispatcher);
        LevelCommand.register(dispatcher);
        AttributeCommand.register(dispatcher);
        StructureCommand.register(dispatcher);
    }
}
