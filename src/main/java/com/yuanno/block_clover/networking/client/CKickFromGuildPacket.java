package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.FactionHelper;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.guild.Guild;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncWorldDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CKickFromGuildPacket {

    private UUID uuid;

    public CKickFromGuildPacket()
    {}

    public CKickFromGuildPacket(UUID uuid)
    {
        this.uuid = uuid;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUUID(this.uuid);
    }

    public static CKickFromGuildPacket decode(PacketBuffer buffer)
    {
        CKickFromGuildPacket msg = new CKickFromGuildPacket();
        msg.uuid = buffer.readUUID();
        return msg;
    }

    public static void handle(CKickFromGuildPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity sender = contextSupplier.get().getSender();
                UUID uuid = message.uuid;
                ExtendedWorldData extendedWorldData = ExtendedWorldData.get(sender.level);
                Guild guild = extendedWorldData.getCrewWithCaptain(sender.getUUID());
                PlayerEntity memberPlayer = sender.level.getPlayerByUUID(uuid);

                if (guild != null && guild.hasMember(uuid))
                {
                    FactionHelper.sendMessageToCrew(sender.level, guild, new TranslationTextComponent("%s has been kicked from the guild", guild.getMember(uuid).getUsername()));
                    extendedWorldData.removeCrewMember(guild, uuid);
                    if (memberPlayer != null)
                        PacketHandler.sendTo(new SSyncWorldDataPacket(extendedWorldData), memberPlayer);
                    FactionHelper.sendUpdateMessageToCrew(sender.level, guild);
                }
            });
        }
        contextSupplier.get().setPacketHandled(true);
    }
}
