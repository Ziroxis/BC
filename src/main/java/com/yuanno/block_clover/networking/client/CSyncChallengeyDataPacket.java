package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncChallengeDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncChallengeyDataPacket
{
	private INBT data;

	public CSyncChallengeyDataPacket() {}

	public CSyncChallengeyDataPacket(IChallengesData props)
	{
		this.data = new CompoundNBT();
		this.data = ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeNbt((CompoundNBT) this.data);
	}

	public static CSyncChallengeyDataPacket decode(PacketBuffer buffer)
	{
		CSyncChallengeyDataPacket msg = new CSyncChallengeyDataPacket();
		msg.data = buffer.readNbt();
		return msg;
	}

	public static void handle(CSyncChallengeyDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IChallengesData props = ChallengesDataCapability.get(player);

				ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, props, null, message.data);
				
				PacketHandler.sendToAllTrackingAndSelf(new SSyncChallengeDataPacket(player.getId(), props), (ServerPlayerEntity) player);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
