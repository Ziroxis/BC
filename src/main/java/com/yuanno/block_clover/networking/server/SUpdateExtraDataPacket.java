package com.yuanno.block_clover.networking.server;

import com.google.common.base.Strings;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SUpdateExtraDataPacket
{
	private int entityId;
	private String abilityId;
	private CompoundNBT extraData;
	private boolean isEquipped;

	public SUpdateExtraDataPacket()
	{
	}

	public SUpdateExtraDataPacket(PlayerEntity player, Ability ability)
	{
		this.entityId = player.getId();
		this.abilityId = Beapi.getResourceName(ability.getName());
		this.isEquipped = true;
		
		if (ability instanceof PassiveAbility)
			this.isEquipped = false;
		
		if (ability instanceof IExtraUpdateData)
			this.extraData = ((IExtraUpdateData) ability).getExtraData();
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.isEquipped);
		buffer.writeInt(this.entityId);
		int len = this.abilityId.length();
		buffer.writeInt(len);
		buffer.writeUtf(this.abilityId, len);
		buffer.writeBoolean(this.extraData != null);
		if (this.extraData != null)
			buffer.writeNbt(this.extraData);
	}

	public static SUpdateExtraDataPacket decode(PacketBuffer buffer)
	{
		SUpdateExtraDataPacket msg = new SUpdateExtraDataPacket();
		msg.isEquipped = buffer.readBoolean();
		msg.entityId = buffer.readInt();
		int len = buffer.readInt();
		msg.abilityId = buffer.readUtf(len);
		boolean hasExtraData = buffer.readBoolean();
		if (hasExtraData)
			msg.extraData = buffer.readNbt();
		return msg;
	}

	public static void handle(SUpdateExtraDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SUpdateExtraDataPacket message)
		{
			if (Strings.isNullOrEmpty(message.abilityId))
				return;

			Entity target = Minecraft.getInstance().level.getEntity(message.entityId);

			if (target == null || !(target instanceof PlayerEntity))
				return;

			ResourceLocation res = new ResourceLocation(Main.MODID, message.abilityId);
			AbilityCore templateAbl = AbilityCore.get(res);

			if (templateAbl == null)
				return;

			IAbilityData props = AbilityDataCapability.get((LivingEntity) target);
			Ability ability = null;
			boolean isEquipped = message.isEquipped;
			if (isEquipped)
				ability = props.getEquippedAbility(templateAbl);
			else
				ability = props.getUnlockedAbility(templateAbl);
						
			if (ability == null)
				return;

			if (message.extraData != null && ability instanceof IExtraUpdateData)
				((IExtraUpdateData) ability).setExtraData(message.extraData);
		}
	}
}
