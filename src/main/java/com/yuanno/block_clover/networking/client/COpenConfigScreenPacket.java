package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenConfigScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenPlayerScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenConfigScreenPacket
{
	public COpenConfigScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static COpenConfigScreenPacket decode(PacketBuffer buffer)
	{
		COpenConfigScreenPacket msg = new COpenConfigScreenPacket();

		return msg;
	}
	
	public static void handle(COpenConfigScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				PacketHandler.sendTo(new SOpenConfigScreenPacket(), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
