package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.guild.GuildCreationScreen;
import com.yuanno.block_clover.entities.api.BCentity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenGuildScreenpacket {

    private int questerEntity;
    public SOpenGuildScreenpacket() {}
    public SOpenGuildScreenpacket(int questerEntity)
    {
        this.questerEntity = questerEntity;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.questerEntity);
    }

    public static SOpenGuildScreenpacket decode(PacketBuffer buffer)
    {
        SOpenGuildScreenpacket msg = new SOpenGuildScreenpacket();
        msg.questerEntity = buffer.readInt();
        return msg;
    }

    public static void handle(SOpenGuildScreenpacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenGuildScreenpacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;
            Entity questGiver = Minecraft.getInstance().level.getEntity(message.questerEntity);
            if (!(questGiver instanceof BCentity))
                return;

            Minecraft.getInstance().setScreen(new GuildCreationScreen());
        }
    }
}
