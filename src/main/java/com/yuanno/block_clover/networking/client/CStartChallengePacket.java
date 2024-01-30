package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.world.ChallengesWorldData;
import com.yuanno.block_clover.init.ModRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CStartChallengePacket {
	private ResourceLocation id;
	private int[] entityIds = new int[3];
	private boolean isFree;
	
	public CStartChallengePacket(){}
	
	public CStartChallengePacket(ResourceLocation resourceLocation, LivingEntity[] group, boolean isFree) {
		this.id = resourceLocation;
		for (int i = 0; i < group.length; i++) {
			if (group[i] != null) {
				this.entityIds[i] = group[i].getId();
			}
			else {
				this.entityIds[i] = -1;
			}
		}
		this.isFree = isFree;
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(this.id);
		for (int i = 0; i < this.entityIds.length; i++) {
			buffer.writeInt(this.entityIds[i]);
		}
		buffer.writeBoolean(this.isFree);
	}

	public static CStartChallengePacket decode(PacketBuffer buffer) {
		CStartChallengePacket msg = new CStartChallengePacket();
		msg.id = buffer.readResourceLocation();
		for (int i = 0; i < 3; i++) {
			msg.entityIds[i] = buffer.readInt();
		}
		msg.isFree = buffer.readBoolean();
		return msg;
	}

	public static void handle(CStartChallengePacket message, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				ServerPlayerEntity player = ctx.get().getSender();
				ServerWorld world = (ServerWorld) player.level;
				IChallengesData props = ChallengesDataCapability.get(player);

				ChallengeCore<?> core = ModRegistries.CHALLENGES.getValue(message.id);
				if (core == null) {
					return;
				}

				List<LivingEntity> list = new ArrayList<>();
				for (int i = 0; i < message.entityIds.length; i++) {
					if (message.entityIds[i] <= 0) {
						continue;
					}
					Entity entity = world.getEntity(message.entityIds[i]);
					if (entity != null && entity instanceof LivingEntity) {
						list.add((LivingEntity) entity);
					}
				}
				
				// Lots of checks here just in case
				
				System.out.println(list);
				
				ChallengesWorldData.get().startChallenge((ServerPlayerEntity) player, new ArrayList<>(), core, message.isFree);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
