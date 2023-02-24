package com.yuanno.block_clover.models.projectiles.mercury;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MercuryBulletModel extends EntityModel {
	private final ModelRenderer Silver_Bullets;

	public MercuryBulletModel() {
		texWidth = 32;
		texHeight = 32;

		Silver_Bullets = new ModelRenderer(this);
		Silver_Bullets.setPos(0.0F, 24.0F, 0.0F);
		Silver_Bullets.texOffs(0, 0).addBox(-3.0F, -15.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		Silver_Bullets.texOffs(12, 10).addBox(-2.0F, -13.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		Silver_Bullets.texOffs(0, 8).addBox(-2.0F, -17.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		Silver_Bullets.render(matrixStack, buffer, packedLight, packedOverlay);
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