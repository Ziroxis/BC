package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModRenderTypes;
import com.yuanno.block_clover.spells.earth.EarthGlovesAbility;
import com.yuanno.block_clover.spells.misc.ManaSenseAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public class ManaLayerRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
	public ManaLayerRenderer(IEntityRenderer renderer)
	{
		super(renderer);
	}
	
	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		if(entity == player)
			return;

		IEntityStats entityData = EntityStatsCapability.get(player);
		IAbilityData abilityData = AbilityDataCapability.get(player);

		Ability ability = abilityData.getEquippedAbility(ManaSenseAbility.INSTANCE);
		boolean isActive = ability != null && ((ManaSenseAbility) ability).isContinuous();
		
		if (isActive)
		{
			int finalPower = entityData.getLevel() + 50;
			if (entity.distanceTo(player) > finalPower)
				return;

			matrixStack.pushPose();

			String color = "#5555FF";

			if (entity instanceof AnimalEntity)
				color = "#55FF55";
			else if (entity instanceof MonsterEntity)
				color = "#FF0000";
			else if (entity instanceof PlayerEntity)
				color = "#00FFFF";
			
			OutlineLayerBuffer outline = Minecraft.getInstance().renderBuffers().outlineBufferSource();
			Color rgbColor = Beapi.hexToRGB(color);
			float red = rgbColor.getRed() / 255.0f;
			float green = rgbColor.getGreen() / 255.0f;
			float blue = rgbColor.getBlue() / 255.0f;
			outline.setColor((int)(red * 255), (int)(green * 255), (int)(blue * 255), 200);
			IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(this.getTextureLocation(entity)));
			this.getParentModel().renderToBuffer(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6f);
			
			matrixStack.popPose();
		}
	}
}
