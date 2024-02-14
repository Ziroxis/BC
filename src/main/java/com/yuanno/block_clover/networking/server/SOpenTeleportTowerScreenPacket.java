package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.AttributeChoiceScreen;
import com.yuanno.block_clover.client.gui.screen.TeleportMagicTowerScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SOpenTeleportTowerScreenPacket
{
	private ArrayList<String> strings;
	private int size;
	public SOpenTeleportTowerScreenPacket() {}




	public void encode(PacketBuffer buffer)
	{

	}

	public static SOpenTeleportTowerScreenPacket decode(PacketBuffer buffer)
	{
		SOpenTeleportTowerScreenPacket msg = new SOpenTeleportTowerScreenPacket();
		return msg;
	}

	public static void handle(SOpenTeleportTowerScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenTeleportTowerScreenPacket message)
		{
			TeleportMagicTowerScreen.open();
		}
	}
}
