package com.yuanno.block_clover.models.entities.summons.water;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WaterShieldModel extends EntityModel {
	private final ModelRenderer WaterShieldMain;
	private final ModelRenderer WaterShield;

	public WaterShieldModel() {
		texWidth = 256;
		texHeight = 256;

		WaterShieldMain = new ModelRenderer(this);
		WaterShieldMain.setPos(13.0F, 39.0F, 0.0F);
		setRotationAngle(WaterShieldMain, 0.0F, 1.5708F, 0.0F);
		

		WaterShield = new ModelRenderer(this);
		WaterShield.setPos(0.0F, 0.0F, 0.0F);
		WaterShieldMain.addChild(WaterShield);
		WaterShield.texOffs(0, 0).addBox(0.9F, -66.0F, -37.0F, 0.0F, 49.0F, 48.0F, 0.0F, false);
		WaterShield.texOffs(52, 53).addBox(1.0F, -64.0F, -35.0F, 1.0F, 45.0F, 44.0F, 0.0F, false);
		WaterShield.texOffs(98, 0).addBox(2.0F, -60.0F, -31.0F, 1.0F, 37.0F, 36.0F, 0.0F, false);
		WaterShield.texOffs(63, 142).addBox(0.0F, -66.0F, -37.0F, 1.0F, 49.0F, 7.0F, 0.0F, false);
		WaterShield.texOffs(135, 135).addBox(0.0F, -66.0F, 4.0F, 1.0F, 49.0F, 7.0F, 0.0F, false);
		WaterShield.texOffs(0, 108).addBox(0.0F, -66.0F, -30.0F, 1.0F, 7.0F, 34.0F, 0.0F, false);
		WaterShield.texOffs(0, 0).addBox(0.0F, -24.0F, -30.0F, 1.0F, 7.0F, 34.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		WaterShieldMain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}