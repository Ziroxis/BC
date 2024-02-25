package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.client.gui.SpellChoiceScreen;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SOpenSpellChoiceScreenPacket
{
	private ArrayList<Ability> abilities = new ArrayList<>();
	private int size;


	public SOpenSpellChoiceScreenPacket() {}

	public SOpenSpellChoiceScreenPacket(ArrayList<Ability> abilities)
	{
		this.abilities = abilities;
		this.size = abilities.size();
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.size);
		for (int i = 0; i < this.size; i++)
		{
			buffer.writeUtf(this.abilities.get(i).getCore().getRegistryName().toString());
		}
	}

	public static SOpenSpellChoiceScreenPacket decode(PacketBuffer buffer)
	{
		SOpenSpellChoiceScreenPacket msg = new SOpenSpellChoiceScreenPacket();
		msg.size = buffer.readInt();
		ArrayList<Ability> abilities = new ArrayList<>();
		for (int i = 0; i < msg.size; i++)
		{
			AbilityCore core = (AbilityCore) GameRegistry.findRegistry(AbilityCore.class).getValue(new ResourceLocation(buffer.readUtf()));
			if (core != null)
			{
				Ability ability = core.createAbility();
				abilities.add(ability);
			}
		}
		msg.abilities = abilities;
		return msg;
	}

	public static void handle(SOpenSpellChoiceScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenSpellChoiceScreenPacket message)
		{
			SpellChoiceScreen.open(message.abilities);
		}
	}
}
