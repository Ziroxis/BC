package com.yuanno.block_clover.setup;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.commands.AbilityCommand;
import com.yuanno.block_clover.commands.AttributeCommand;
import com.yuanno.block_clover.commands.ExperienceCommand;
import com.yuanno.block_clover.commands.LevelCommand;
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

        ExperienceCommand.register(dispatcher);
        AbilityCommand.register(dispatcher);
        LevelCommand.register(dispatcher);
        AttributeCommand.register(dispatcher);
    }
}
