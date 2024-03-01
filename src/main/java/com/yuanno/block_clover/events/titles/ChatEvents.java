package com.yuanno.block_clover.events.titles;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ChatEvents {

    @SubscribeEvent
    public static void onMessage(ServerChatEvent event)
    {
        MinecraftServer server = event.getPlayer().server;
        IEntityStats stats = EntityStatsCapability.get(event.getPlayer());
        if (stats.hasTitle())
        {
            event.setCanceled(true);
            String title = stats.getTitle();
            String chat = "[" + title + "§f]" + "<" + event.getPlayer().getDisplayName().getString() + "> " + event.getMessage();
            for (ServerPlayerEntity player : server.getPlayerList().getPlayers())
            {
                server.sendMessage(new StringTextComponent(chat), player.getUUID());
                player.sendMessage(new StringTextComponent(chat), player.getUUID());
            }
        }
    }
}
