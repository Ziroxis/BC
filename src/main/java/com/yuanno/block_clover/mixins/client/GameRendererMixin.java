package com.yuanno.block_clover.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = GameRenderer.class, priority = 990)
public abstract class GameRendererMixin implements ISelectiveResourceReloadListener
{
	/*
	@Shadow
	@Final
	private Minecraft minecraft;

	@ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 6.0))
	private double getActualAttackRangeInCreative(final double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}

	@ModifyVariable(method = "pick(F)V", at = @At("STORE"), ordinal = 1)
	private double getActualAttackRangeInSurvival0(double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}

	@ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 9.0))
	private double getActualAttackRangeInSurvival1(final double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}


	 */
	/*
	@Inject(
		method = "renderItemInHand", 
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/matrix/MatrixStack;popPose()V",
			shift = At.Shift.AFTER
		)
	)
	private void renderItemInHand(MatrixStack matrixStack, ActiveRenderInfo activeRenderInfo, float partialTicks, CallbackInfo info)
	{
		PlayerEntity player = this.minecraft.player;
		if(player == null)
			return;
		RenderOverlayEvent event = new RenderOverlayEvent(player, matrixStack, activeRenderInfo, partialTicks);
		MinecraftForge.EVENT_BUS.post(event);
		SpecialPotionEffectEvents.renderScreenEffects(player, matrixStack, activeRenderInfo, partialTicks);
	}

	 */
}
