package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CUseAbilityPacket
{
	private int slot;

	public CUseAbilityPacket()
	{
	}

	public CUseAbilityPacket(int slot)
	{
		this.slot = slot;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
	}

	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		msg.slot = buffer.readInt();
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				
				player.level.getProfiler().push("abilityUse");
				
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				Ability abl = abilityDataProps.getEquippedAbility(message.slot);
				
				if (abl == null || player.isSpectator() || !abl.canUse(player))
					return;

				try
				{

					// Only allows cancelable chargeables to be canceled early
					if (abl instanceof ChargeableAbility && abl.isCharging() && !((ChargeableAbility) abl).isCancelable())
						return;
	
					// Stops multiple continuous abilities from being activated if they're not parallel continuous
					if (abl instanceof ContinuousAbility && !abl.isContinuous() && !(abl instanceof IParallelContinuousAbility))
					{
						for (Ability ability : abilityDataProps.getEquippedAbilities())
						{
							if (ability instanceof ContinuousAbility && ability.isContinuous() && !(ability instanceof IParallelContinuousAbility))
							{
								if(true) {
									((ContinuousAbility) ability).endContinuity(player);
								}
								else
									return;
							}
						}
					}
	

					
					abl.use(player);
					//WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl, ), player);
					//WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getId(), devilFruitProps), player);
					//WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityDataProps), player);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					abl.startCooldown(player);
				}
				player.level.getProfiler().pop();
			});
		}

		ctx.get().setPacketHandled(true);
	}

}
