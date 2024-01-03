package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.client.gui.AttributeChoiceScreen;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SOpenAttributeChoiceScreenPacket
{
	private ArrayList<String> strings;
	private int size;
	public SOpenAttributeChoiceScreenPacket() {}

	public SOpenAttributeChoiceScreenPacket(ArrayList<String> strings) {
		this.strings = strings;
		this.size = strings.size();
	}


	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.size);
		for (int i = 0; i < this.size; i++)
		{
			buffer.writeUtf(this.strings.get(i));
		}
	}

	public static SOpenAttributeChoiceScreenPacket decode(PacketBuffer buffer)
	{
		SOpenAttributeChoiceScreenPacket msg = new SOpenAttributeChoiceScreenPacket();
		msg.size = buffer.readInt();
		ArrayList<String> attributes = new ArrayList<>();
		for (int i = 0; i < msg.size; i++)
		{
			String attribute = buffer.readUtf();
			attributes.add(attribute);
		}
		msg.strings = attributes;
		return msg;
	}

	public static void handle(SOpenAttributeChoiceScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenAttributeChoiceScreenPacket message)
		{
			System.out.println(message.strings);
			AttributeChoiceScreen.open(message.strings);
		}
	}
}
