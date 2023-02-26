package com.yuanno.block_clover.models.projectiles.mercury;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MercuryRainModel extends EntityModel {
	private final ModelRenderer MercuryRain;

	public MercuryRainModel() {
		texWidth = 16;
		texHeight = 16;

		MercuryRain = new ModelRenderer(this);
		MercuryRain.setPos(0.0F, 24.0F, 0.0F);
		MercuryRain.texOffs(0, 0).addBox(-1.0F, -20.0F, -1.0F, 0.0F, 11.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		MercuryRain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(Entity e, float f, float f1, float f2, float f3, float f4) {
	}
}