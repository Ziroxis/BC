package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.guild.GuildInviteScreen;
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

    private int playerTarget;
    private int playerCaptain;
    public SOpenGuildScreenInvitationpacket()
    {

    }
    public SOpenGuildScreenInvitationpacket(int playerTarget, int playerCaptain)
    {
        this.playerTarget = playerTarget;
        this.playerCaptain = playerCaptain;
    }
    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.playerTarget);
        buffer.writeInt(this.playerCaptain);
    }

    public static SOpenGuildScreenInvitationpacket decode(PacketBuffer buffer)
    {
        SOpenGuildScreenInvitationpacket msg = new SOpenGuildScreenInvitationpacket();
        msg.playerTarget = buffer.readInt();
        msg.playerCaptain = buffer.readInt();

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
            Entity playerCaptain = Minecraft.getInstance().level.getEntity(message.playerCaptain);
            if (!(playerCaptain instanceof PlayerEntity))
                return;
            Entity playerTarget = Minecraft.getInstance().level.getEntity(message.playerTarget);
            if (!(playerTarget instanceof PlayerEntity))
                return;

            Minecraft.getInstance().setScreen(new GuildInviteScreen((PlayerEntity) playerTarget,(PlayerEntity) playerCaptain));
        }
    }
}
