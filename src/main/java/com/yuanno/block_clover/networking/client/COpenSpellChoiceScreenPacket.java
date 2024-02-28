package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenConfigScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenSpellChoiceScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class COpenSpellChoiceScreenPacket
{
	private ArrayList<Ability> abilities = new ArrayList<>();
	private int size;


	public COpenSpellChoiceScreenPacket() {}

	public COpenSpellChoiceScreenPacket(ArrayList<Ability> abilities)
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

	public static COpenSpellChoiceScreenPacket decode(PacketBuffer buffer)
	{
		COpenSpellChoiceScreenPacket msg = new COpenSpellChoiceScreenPacket();
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
	
	public static void handle(COpenSpellChoiceScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				PacketHandler.sendTo(new SOpenSpellChoiceScreenPacket(message.abilities), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
