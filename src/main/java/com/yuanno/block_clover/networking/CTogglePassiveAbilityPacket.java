package com.yuanno.block_clover.networking;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CTogglePassiveAbilityPacket
{
	private String abilityName;
	private boolean flag;
	
	public CTogglePassiveAbilityPacket() {}
	
	public CTogglePassiveAbilityPacket(Ability ability, boolean flag)
	{
		this.abilityName = Beapi.getResourceName(ability.getName());
		this.flag = flag;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.abilityName.length());
		buffer.writeUtf(this.abilityName, this.abilityName.length());
		buffer.writeBoolean(this.flag);
	}
	
	public static CTogglePassiveAbilityPacket decode(PacketBuffer buffer)
	{
		CTogglePassiveAbilityPacket msg = new CTogglePassiveAbilityPacket();
		int len = buffer.readInt();
		msg.abilityName = buffer.readUtf(len);
		msg.flag = buffer.readBoolean();
		return msg;
	}

	public static void handle(CTogglePassiveAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
			
				Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(Main.MODID, message.abilityName)).create();
				Ability unlockedAbility = abilityDataProps.getUnlockedAbility(ability);
				
				if(unlockedAbility instanceof PassiveAbility)
				{
					PassiveAbility passive = ((PassiveAbility)unlockedAbility);
					passive.setPause(message.flag);
					//WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, passive), player);
				}
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}