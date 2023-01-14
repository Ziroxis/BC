package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.GuildCreationScreen;
import com.yuanno.block_clover.entities.BCentity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenGuildScreenInvitationpacket {

    public SOpenGuildScreenInvitationpacket()
    {

    }

    public void encode(PacketBuffer buffer)
    {
    }

    public static SOpenGuildScreenInvitationpacket decode(PacketBuffer buffer)
    {
        SOpenGuildScreenInvitationpacket msg = new SOpenGuildScreenInvitationpacket();
        return msg;
    }

    public static void handle(SOpenGuildScreenInvitationpacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenGuildScreenInvitationpacket message)
        {

            Minecraft.getInstance().setScreen(new GuildCreationScreen());
        }
    }
}
