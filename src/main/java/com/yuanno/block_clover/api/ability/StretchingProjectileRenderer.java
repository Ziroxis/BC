package com.yuanno.block_clover.api.ability;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.init.ModRenderTypes;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.InvocationTargetException;

@OnlyIn(Dist.CLIENT)
public class StretchingProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>> extends AbilityProjectileRenderer<T, M>
{
	private M stretchModel;
	private float stretchScaleX = 1, stretchScaleY = 1, stretchScaleZ = 1;

	public StretchingProjectileRenderer(EntityRendererManager renderManager, M model, M stretchModel)
	{
		super(renderManager, model, null);
		this.stretchModel = stretchModel;
	}

	public void setStretchScale(double scaleX, double scaleY, double scaleZ)
	{
		this.stretchScaleX = (float) scaleX;
		this.stretchScaleY = (float) scaleY;
		this.stretchScaleZ = (float) scaleZ;
	}

	@Override
	public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
	{
		if (entity.tickCount < 2)
			return;
		
		if(entity.getThrower() == null || !entity.getThrower().isAlive())
		{
			entity.remove();
			return;
		}	

		Vector3d originPos = entity.getThrower().position();

		Vector3d entityPos = new Vector3d(
			MathHelper.lerp(partialTicks, entity.xo, entity.getX()), 
			MathHelper.lerp(partialTicks, entity.yo, entity.getY()), 
			MathHelper.lerp(partialTicks, entity.zo, entity.getZ()));
		Vector3d stretchVec = entityPos.subtract(originPos);

		if (this.stretchModel != null)
		{
			matrixStack.pushPose();
			{
				matrixStack.mulPose(Vector3f.YP.rotationDegrees(entity.yRotO + (entity.yRot - entity.yRotO) * partialTicks - 180.0F));
				matrixStack.mulPose(Vector3f.XP.rotationDegrees(entity.xRotO + (entity.xRot - entity.xRotO) * partialTicks));
				matrixStack.mulPose(new Quaternion(Vector3f.ZP, 180, true));

				float modelLength = this.stretchScaleZ / 16F;
				float modelOffset = 4 / 16F;
				float stretchLength = (float) stretchVec.length();
				matrixStack.translate(0.0F, 0.0F, -modelOffset);
				matrixStack.scale(this.stretchScaleX, this.stretchScaleY, (stretchLength - 2 * modelOffset) / modelLength);
				matrixStack.translate(0.0F, 0.0F, modelOffset);
				
				RenderType type;
				ResourceLocation finalTexture = this.getTextureLocation(entity);
				if (finalTexture == null)
					type = this.isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
				else
					type = RenderType.entityTranslucent(finalTexture);

				boolean isSlim = false;
				if(entity.getThrower() instanceof ClientPlayerEntity)
					isSlim = ((AbstractClientPlayerEntity) entity.getThrower()).getModelName().equals("slim");

				if(isSlim)
				{
					try
					{
						this.stretchModel = (M) this.stretchModel.getClass().getConstructor(boolean.class).newInstance(isSlim);
					}
					catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
					{
						e.printStackTrace();
					}
				}

				IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
				this.stretchModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, this.getRGB().getRed() / 255F, this.getRGB().getGreen() / 255F, this.getRGB().getBlue() / 255F, this.getRGB().getAlpha() / 255F);
				

			}
			matrixStack.popPose();
		}
		
		super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	public static class Factory extends AbilityProjectileRenderer.Factory
	{
		private EntityModel stretchModel;
		protected double stretchScaleX = 1, stretchScaleY = 1, stretchScaleZ = 8;

		public Factory(EntityModel stretchModel)
		{
			super(null);
			this.stretchModel = stretchModel;
		}
		
		public Factory(EntityModel tipModel, EntityModel stretchModel)
		{
			super(tipModel);
			this.stretchModel = stretchModel;
		}

		public Factory setStretchScale(double scaleX, double scaleY)
		{
			return setStretchScale(scaleX, scaleY, 8);
		}
		
		public Factory setStretchScale(double scaleX, double scaleY, double scaleZ)
		{
			this.stretchScaleX = scaleX;
			this.stretchScaleY = scaleY;
			this.stretchScaleZ = scaleZ;
			return this;
		}

		@Override
		public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager)
		{
			StretchingProjectileRenderer renderer = new StretchingProjectileRenderer(manager, this.model, this.stretchModel);
			renderer.setStretchScale(this.stretchScaleX, this.stretchScaleY, this.stretchScaleZ);
			renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
			renderer.setColor(this.red, this.green, this.blue, this.alpha);
			//renderer.setPlayerTexture(this.usePlayerTexture);
			renderer.setGlowing(this.isGlowing);
			return renderer;
		}
	}
}
