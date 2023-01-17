package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.FactionHelper;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.GuildEvents;
import com.yuanno.block_clover.guild.Guild;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncWorldDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CLeaveGuildPacket {

    public CLeaveGuildPacket()
    {

    }

    public void encode(PacketBuffer buffer)
    {
    }


    public static CLeaveGuildPacket decode(PacketBuffer buffer)
    {
        CLeaveGuildPacket msg = new CLeaveGuildPacket();
        return msg;
    }

    public static void handle(CLeaveGuildPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                UUID uuid = player.getUUID();
                ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);

                Guild guild = extendedWorldData.getGuildWithMember(uuid);

                if (guild != null)
                {
                    GuildEvents.Leave event = new GuildEvents.Leave(player, guild);
                    if (!MinecraftForge.EVENT_BUS.post(event))
                    {
                        boolean captainChange = false;
                        FactionHelper.sendMessageToCrew(player.level, guild, new TranslationTextComponent("Player %s has left the guild", player.getName().getString()));
                        if(guild.getCaptain().getUUID().equals(uuid))
                            captainChange = true;
                        extendedWorldData.removeCrewMember(guild, uuid);
                        if(captainChange && guild.getCaptain() != null)
                            FactionHelper.sendMessageToCrew(player.level, guild, new TranslationTextComponent("%s has become the new captain", guild.getCaptain().getUsername()));
                        if(guild.getMembers().size() <= 0)
                            extendedWorldData.removeCrew(guild);
                        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), player);
                        FactionHelper.sendUpdateMessageToCrew(player.level, guild);
                    }
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
