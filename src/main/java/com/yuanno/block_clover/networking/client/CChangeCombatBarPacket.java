package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SChangeCombatBarPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CChangeCombatBarPacket
{
	private int dir = 0;
	
	public CChangeCombatBarPacket() {}
	
	public CChangeCombatBarPacket(int dir)
	{
		this.dir = dir;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.dir);
	}

	public static CChangeCombatBarPacket decode(PacketBuffer buffer)
	{
		CChangeCombatBarPacket msg = new CChangeCombatBarPacket();
		msg.dir = buffer.readInt();
		return msg;
	}
	
	public static void handle(CChangeCombatBarPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityProps = AbilityDataCapability.get(player);
								
				if(message.dir == 0)
				{
					if(((abilityProps.getCombatBarSet() + 1) * 8) < (10))
						abilityProps.nextCombatBarSet();
					else
						abilityProps.setCombatBarSet(0);
				}
				else
				{
					if(abilityProps.getCombatBarSet() > 0)
						abilityProps.prevCombatBarSet();
					else
						abilityProps.setCombatBarSet(1);
				}
				
				PacketHandler.sendTo(new SChangeCombatBarPacket(abilityProps.getCombatBarSet()), player);
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
