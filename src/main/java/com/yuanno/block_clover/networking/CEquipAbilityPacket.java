package com.yuanno.block_clover.networking;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CEquipAbilityPacket
{
	private int slot;
	private ResourceLocation abilityId;
	
	public CEquipAbilityPacket() {}
	
	public CEquipAbilityPacket(int id, Ability ability)
	{
		this.slot = id;
		this.abilityId = ability.getCore().getRegistryName();
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
		buffer.writeResourceLocation(this.abilityId);
	}
	
	public static CEquipAbilityPacket decode(PacketBuffer buffer)
	{
		CEquipAbilityPacket msg = new CEquipAbilityPacket();
		msg.slot = buffer.readInt();
		msg.abilityId = buffer.readResourceLocation();
		return msg;
	}

	public static void handle(CEquipAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);

				Ability ability = ((AbilityCore)GameRegistry.findRegistry(AbilityCore.class).getValue(message.abilityId)).createAbility();

				abilityDataProps.setEquippedAbility(message.slot, ability);
				PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), player);
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}