package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ChatEvents {

    @SubscribeEvent
    public static void onMessage(ServerChatEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.hasTitle())
        {
            event.setCanceled(true);
            String title = stats.getTitle();
            String chat = "[" + title + "§f]" + "<" + player.getDisplayName().getString() + "> " + event.getMessage();
            player.sendMessage(new StringTextComponent(chat), player.getUUID());
        }
    }
}
