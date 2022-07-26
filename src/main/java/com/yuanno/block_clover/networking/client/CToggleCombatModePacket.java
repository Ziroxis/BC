package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CToggleCombatModePacket
{
	private boolean combatMode = false;
	
	public CToggleCombatModePacket() {}

	public CToggleCombatModePacket(boolean combatMode) 
	{
		this.combatMode = combatMode;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.combatMode);
	}
	
	public static CToggleCombatModePacket decode(PacketBuffer buffer)
	{
		CToggleCombatModePacket msg = new CToggleCombatModePacket();
		msg.combatMode = buffer.readBoolean();
		return msg;
	}

	public static void handle(CToggleCombatModePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IEntityStats props = EntityStatsCapability.get(player);
				
				props.setCombatMode(message.combatMode);							
			});			
		}
		ctx.get().setPacketHandled(true);
	}

}
