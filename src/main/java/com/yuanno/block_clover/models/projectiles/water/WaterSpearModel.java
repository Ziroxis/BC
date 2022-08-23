package com.yuanno.block_clover.models.projectiles.water;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WaterSpearModel extends EntityModel {
	private final ModelRenderer WaterSpearMain;
	private final ModelRenderer WaterSpear;
	private final ModelRenderer WaterSpearDetails;
	private final ModelRenderer Tier1;
	private final ModelRenderer Tier2;
	private final ModelRenderer WaterSpearSwirls;
	private final ModelRenderer Swirl;
	private final ModelRenderer SwirlG1;
	private final ModelRenderer Swirl4_r1;
	private final ModelRenderer Swirl3_r1;
	private final ModelRenderer SwirlG2;
	private final ModelRenderer Swirl4_r2;
	private final ModelRenderer Swirl3_r2;

	public WaterSpearModel() {
		texWidth = 256;
		texHeight = 256;

		WaterSpearMain = new ModelRenderer(this);
		WaterSpearMain.setPos(1.0F, 24.0F, 24.0F);
		

		WaterSpear = new ModelRenderer(this);
		WaterSpear.setPos(0.0F, 0.0F, 0.0F);
		WaterSpearMain.addChild(WaterSpear);
		WaterSpear.texOffs(0, 0).addBox(-1.0F, -15.0F, -47.0F, 1.0F, 1.0F, 72.0F, 0.0F, false);
		WaterSpear.texOffs(16, 30).addBox(-1.5F, -15.5F, -56.0F, 2.0F, 2.0F, 12.0F, 0.0F, false);
		WaterSpear.texOffs(18, 15).addBox(-2.0F, -16.0F, -61.0F, 3.0F, 3.0F, 12.0F, 0.0F, false);
		WaterSpear.texOffs(41, 41).addBox(-3.0F, -17.0F, -52.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);
		WaterSpear.texOffs(32, 30).addBox(-2.5F, -16.5F, -55.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);
		WaterSpear.texOffs(0, 27).addBox(-1.5F, -15.5F, -64.0F, 2.0F, 2.0F, 12.0F, 0.0F, false);
		WaterSpear.texOffs(36, 7).addBox(-1.0F, -15.0F, -69.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		WaterSpearDetails = new ModelRenderer(this);
		WaterSpearDetails.setPos(0.0F, 0.0F, 0.0F);
		WaterSpear.addChild(WaterSpearDetails);
		

		Tier1 = new ModelRenderer(this);
		Tier1.setPos(0.0F, 0.0F, 0.0F);
		WaterSpearDetails.addChild(Tier1);
		Tier1.texOffs(0, 6).addBox(2.5F, -17.5F, -55.0F, 0.0F, 6.0F, 15.0F, 0.0F, false);
		Tier1.texOffs(0, 0).addBox(-3.5F, -17.5F, -55.0F, 0.0F, 6.0F, 15.0F, 0.0F, false);
		Tier1.texOffs(12, 0).addBox(-3.5F, -17.5F, -55.0F, 6.0F, 0.0F, 15.0F, 0.0F, false);
		Tier1.texOffs(0, 0).addBox(-3.5F, -11.5F, -55.0F, 6.0F, 0.0F, 15.0F, 0.0F, false);

		Tier2 = new ModelRenderer(this);
		Tier2.setPos(0.0F, 0.0F, -13.0F);
		WaterSpearDetails.addChild(Tier2);
		Tier2.texOffs(36, 9).addBox(1.5F, -16.5F, -50.0F, 0.0F, 4.0F, 7.0F, 0.0F, false);
		Tier2.texOffs(0, 34).addBox(-2.5F, -16.5F, -50.0F, 0.0F, 4.0F, 7.0F, 0.0F, false);
		Tier2.texOffs(0, 7).addBox(-2.5F, -16.5F, -50.0F, 4.0F, 0.0F, 7.0F, 0.0F, false);
		Tier2.texOffs(0, 0).addBox(-2.5F, -12.5F, -50.0F, 4.0F, 0.0F, 7.0F, 0.0F, false);

		WaterSpearSwirls = new ModelRenderer(this);
		WaterSpearSwirls.setPos(22.0F, -18.0F, 0.0F);
		WaterSpearMain.addChild(WaterSpearSwirls);
		setRotationAngle(WaterSpearSwirls, 0.0F, 0.0F, -1.5708F);
		

		Swirl = new ModelRenderer(this);
		Swirl.setPos(-22.0F, -22.0F, 10.0F);
		WaterSpearSwirls.addChild(Swirl);
		

		SwirlG1 = new ModelRenderer(this);
		SwirlG1.setPos(19.7321F, -6.0F, 0.0F);
		Swirl.addChild(SwirlG1);
		SwirlG1.texOffs(0, 37).addBox(0.0F, 4.0F, -50.0F, 0.0F, 3.0F, 65.0F, 0.0F, false);
		SwirlG1.texOffs(0, 34).addBox(-3.0F, 4.0F, -50.0F, 0.0F, 3.0F, 65.0F, 0.0F, false);

		Swirl4_r1 = new ModelRenderer(this);
		Swirl4_r1.setPos(0.0F, 0.0F, 0.0F);
		SwirlG1.addChild(Swirl4_r1);
		setRotationAngle(Swirl4_r1, 0.0F, 0.0F, -1.5708F);
		Swirl4_r1.texOffs(0, 28).addBox(-4.0F, -3.0F, -50.0F, 0.0F, 3.0F, 65.0F, 0.0F, false);

		Swirl3_r1 = new ModelRenderer(this);
		Swirl3_r1.setPos(0.0F, 3.0F, 0.0F);
		SwirlG1.addChild(Swirl3_r1);
		setRotationAngle(Swirl3_r1, 0.0F, 0.0F, -1.5708F);
		Swirl3_r1.texOffs(0, 31).addBox(-4.0F, -3.0F, -50.0F, 0.0F, 3.0F, 65.0F, 0.0F, false);

		SwirlG2 = new ModelRenderer(this);
		SwirlG2.setPos(19.7321F, -6.0F, 0.0F);
		Swirl.addChild(SwirlG2);
		SwirlG2.texOffs(0, 23).addBox(1.0F, 3.0F, -50.0F, 0.0F, 5.0F, 65.0F, 0.0F, false);
		SwirlG2.texOffs(0, 18).addBox(-4.0F, 3.0F, -50.0F, 0.0F, 5.0F, 65.0F, 0.0F, false);

		Swirl4_r2 = new ModelRenderer(this);
		Swirl4_r2.setPos(0.0F, -1.0F, 0.0F);
		SwirlG2.addChild(Swirl4_r2);
		setRotationAngle(Swirl4_r2, 0.0F, 0.0F, -1.5708F);
		Swirl4_r2.texOffs(0, 8).addBox(-4.0F, -4.0F, -50.0F, 0.0F, 5.0F, 65.0F, 0.0F, false);

		Swirl3_r2 = new ModelRenderer(this);
		Swirl3_r2.setPos(0.0F, 4.0F, 0.0F);
		SwirlG2.addChild(Swirl3_r2);
		setRotationAngle(Swirl3_r2, 0.0F, 0.0F, -1.5708F);
		Swirl3_r2.texOffs(0, 13).addBox(-4.0F, -4.0F, -50.0F, 0.0F, 5.0F, 65.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		WaterSpearMain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}