package com.yuanno.block_clover.mixins;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CClientStatusPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetHandler.class)
public abstract class ServerPlayNetHandlerMixin implements IServerPlayNetHandler
{
	@Shadow
	public ServerPlayerEntity player;

	@Shadow
	@Final
	public MinecraftServer server;

	/*
	@ModifyConstant(
		method = "handleInteract(Lnet/minecraft/network/play/client/CUseEntityPacket;)V", 
		constant = @Constant(doubleValue = 36.0)
	)
	public double getActualAttackRange(double attackRange, CUseEntityPacket packet)
	{
		if (packet.getAction() == CUseEntityPacket.Action.ATTACK)
			return AttributeHelper.getSquaredAttackRangeDistance(this.player, attackRange);

		return AttributeHelper.getSquaredAttackRangeDistance(this.player, attackRange);
	}

	 */
	
	@Inject(
		method = "handleClientCommand", 
		at = @At(
			value = "INVOKE", 
			target = "Lnet/minecraft/server/management/PlayerList;respawn(Lnet/minecraft/entity/player/ServerPlayerEntity;Z)Lnet/minecraft/entity/player/ServerPlayerEntity;", 
			shift = At.Shift.BEFORE
		),
		cancellable = true
	)
	public void processClientStatus(CClientStatusPacket packet, CallbackInfo callback)
	{
//		IDevilFruit props = DevilFruitCapability.get(this.player);
//
//		if(props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !props.getZoanPoint().equalsIgnoreCase(YomiMorphInfo.INSTANCE.getForm()))
//		{
//			this.player = this.server.getPlayerList().respawn(this.player, false);
//			callback.cancel();
//		}
	}
}
