package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.screen.config.ConfigScreen;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenConfigScreenPacket
{
	public SOpenConfigScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenConfigScreenPacket decode(PacketBuffer buffer)
	{
		SOpenConfigScreenPacket msg = new SOpenConfigScreenPacket();
		return msg;
	}

	public static void handle(SOpenConfigScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenConfigScreenPacket message)
		{
			ConfigScreen.open();
		}
	}
}
