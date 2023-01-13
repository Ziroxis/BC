package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.guild.Guild;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncWorldDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CCreateGuildPacket {

    private String name;

    public CCreateGuildPacket()
    {
    }

    public CCreateGuildPacket(String name)
    {
        this.name = name;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.name.length());
        buffer.writeUtf(this.name);
    }

    public static CCreateGuildPacket decode(PacketBuffer buffer)
    {
        CCreateGuildPacket msg = new CCreateGuildPacket();
        int len = buffer.readInt();
        msg.name = buffer.readUtf(len);
        return msg;
    }

    public static void handle(CCreateGuildPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IEntityStats props = EntityStatsCapability.get(player);
                ExtendedWorldData worldProps = ExtendedWorldData.get(player.level);

                //boolean hasSakeBottle = !player.getMainHandItem().isEmpty() && player.getMainHandItem().getItem().equals(ModItems.SAKE_BOTTLE.get());
                boolean isAlreadyInCrew = worldProps.getGuildWithMember(player.getUUID()) != null;

                if(isAlreadyInCrew)
                    return;

                Guild crew = new Guild(message.name, player);
                worldProps.addCrew(crew);
                crew.create(player.level);


                TranslationTextComponent newCrewMsg = new TranslationTextComponent("A new guild just formed, %s!", message.name);
                for (PlayerEntity target : player.level.players())
                {
                    target.sendMessage(new StringTextComponent(TextFormatting.GOLD + newCrewMsg.getString()), Util.NIL_UUID);
                }


                PacketHandler.sendToAll(new SSyncWorldDataPacket(worldProps));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
