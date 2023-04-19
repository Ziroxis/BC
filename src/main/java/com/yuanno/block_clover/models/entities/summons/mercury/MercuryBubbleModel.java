package com.yuanno.block_clover.models.entities.summons.mercury;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MercuryBubbleModel extends EntityModel {
	private final ModelRenderer Biggest_Mercury_Bubble;
	public MercuryBubbleModel() {
		texWidth = 256;
		texHeight = 256;

		Biggest_Mercury_Bubble = new ModelRenderer(this);
		Biggest_Mercury_Bubble.setPos(0.0F, 24.0F, 0.0F);
		Biggest_Mercury_Bubble.texOffs(52, 86).addBox(-14.0F, -37.0F, -16.0F, 24.0F, 37.0F, 2.0F, 0.0F, false);
		Biggest_Mercury_Bubble.texOffs(0, 86).addBox(-14.0F, -37.0F, 9.0F, 24.0F, 37.0F, 2.0F, 0.0F, false);
		Biggest_Mercury_Bubble.texOffs(50, 26).addBox(-16.0F, -37.0F, -14.0F, 2.0F, 37.0F, 23.0F, 0.0F, false);
		Biggest_Mercury_Bubble.texOffs(0, 26).addBox(10.0F, -37.0F, -14.0F, 2.0F, 37.0F, 23.0F, 0.0F, false);
		Biggest_Mercury_Bubble.texOffs(0, 0).addBox(-14.0F, -40.0F, -14.0F, 24.0F, 3.0F, 23.0F, 0.0F, false);	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		Biggest_Mercury_Bubble.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}