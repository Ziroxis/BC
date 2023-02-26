package com.yuanno.block_clover.models.projectiles.mercury;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MercurySpearModel extends EntityModel {
	private final ModelRenderer Silver_Spear;

	public MercurySpearModel() {
		texWidth = 128;
		texHeight = 128;

		Silver_Spear = new ModelRenderer(this);
		Silver_Spear.setPos(0.0F, 24.0F, 0.0F);
		Silver_Spear.texOffs(0, 0).addBox(-1.0F, -11.0F, -16.0F, 2.0F, 2.0F, 28.0F, 0.0F, false);
		Silver_Spear.texOffs(32, 0).addBox(-2.0F, -11.0F, -12.0F, 1.0F, 2.0F, 23.0F, 0.0F, false);
		Silver_Spear.texOffs(0, 30).addBox(1.0F, -11.0F, -12.0F, 1.0F, 2.0F, 23.0F, 0.0F, false);
		Silver_Spear.texOffs(0, 5).addBox(-5.0F, -11.0F, -16.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		Silver_Spear.texOffs(0, 0).addBox(1.0F, -11.0F, -16.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		Silver_Spear.texOffs(10, 3).addBox(-7.0F, -10.0F, -15.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Silver_Spear.texOffs(0, 10).addBox(5.0F, -10.0F, -15.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Silver_Spear.texOffs(25, 30).addBox(-1.0F, -12.0F, -12.0F, 2.0F, 1.0F, 21.0F, 0.0F, false);
		Silver_Spear.texOffs(9, 7).addBox(-1.0F, -11.0F, -19.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		Silver_Spear.render(matrixStack, buffer, packedLight, packedOverlay);
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