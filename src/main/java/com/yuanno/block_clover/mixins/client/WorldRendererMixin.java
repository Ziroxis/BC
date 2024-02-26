package com.yuanno.block_clover.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin
{
    @Accessor("viewArea")
    public abstract void setViewFrustum(ViewFrustum fluidRenderer);

    @Accessor("viewArea")
    public abstract ViewFrustum getViewFrustum();
    
    @Accessor("renderBuffers")
    public abstract RenderTypeBuffers getRenderTypeTextures();

	/*
	@Inject(
		method = "renderEntity", 
		at = @At(value = "HEAD")
	)
	public void renderEntity(Entity pEntity, double pCamX, double pCamY, double pCamZ, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, CallbackInfo callback)
	{
		if(pBuffer instanceof OutlineLayerBuffer)
		{
			OutlineLayerBuffer buffer = (OutlineLayerBuffer) pBuffer;
			

			buffer.setColor(event.getColor().getRed(), event.getColor().getGreen(), event.getColor().getBlue(), event.getColor().getAlpha());
		}
	}

	 */
}