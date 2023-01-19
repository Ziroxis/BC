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

public class CJoinGuildPacket {

    private UUID playerCaptain;

    public CJoinGuildPacket()
    {

    }

    public CJoinGuildPacket(UUID uuid)
    {
        this.playerCaptain = uuid;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUUID(this.playerCaptain);
    }

    public static CJoinGuildPacket decode(PacketBuffer packetBuffer)
    {
        CJoinGuildPacket msg = new CJoinGuildPacket();
        msg.playerCaptain = packetBuffer.readUUID();
        return msg;
    }

    public static void handle(CJoinGuildPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity playerTarget = contextSupplier.get().getSender();
                UUID uuidCaptain = message.playerCaptain;
                ExtendedWorldData extendedWorldData = ExtendedWorldData.get(playerTarget.level);
                Guild guild = extendedWorldData.getCrewWithCaptain(uuidCaptain);

                if (guild != null && !guild.hasMember(playerTarget.getUUID()))
                {
                    GuildEvents.Join events = new GuildEvents.Join(playerTarget, guild);
                    if (!MinecraftForge.EVENT_BUS.post(events))
                    {
                        extendedWorldData.addCrewMember(guild, playerTarget);
                        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), playerTarget);
                        FactionHelper.sendUpdateMessageToCrew(playerTarget.level, guild);
                        FactionHelper.sendMessageToCrew(playerTarget.level, guild, new TranslationTextComponent("%s has joined the guild", guild.getMember(playerTarget.getUUID()).getUsername()));
                    }
                }
            });
        }
        contextSupplier.get().setPacketHandled(true);    }
}
