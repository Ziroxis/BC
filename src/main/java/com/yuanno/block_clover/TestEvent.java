package com.yuanno.block_clover;

import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.COpenDevilSummoningScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenpacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TestEvent {

    @SubscribeEvent
    public static void chatEventServer(ServerChatEvent event)
    {
        if (event.getMessage().equals("summoningritual"))
        {
            PlayerEntity player = event.getPlayer();
            PacketHandler.sendTo(new SOpenDevilSummoningScreenpacket(), player);
        }

    }


}