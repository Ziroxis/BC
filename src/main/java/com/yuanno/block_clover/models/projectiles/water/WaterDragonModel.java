package com.yuanno.block_clover.models.projectiles.water;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WaterDragonModel extends EntityModel {
	private final ModelRenderer WaterDragonMain;
	private final ModelRenderer WaterDragonHead;
	private final ModelRenderer UpperSnout;
	private final ModelRenderer TopTeeth;
	private final ModelRenderer Tooth23_r1;
	private final ModelRenderer LowerSnout;
	private final ModelRenderer BottomTeeth;
	private final ModelRenderer Tooth23_r2;
	private final ModelRenderer WaterDragonHeadDetails;
	private final ModelRenderer HeadD3_r1;
	private final ModelRenderer HeadD2_r1;
	private final ModelRenderer HeadD1_r1;
	private final ModelRenderer WaterDragonBody;
	private final ModelRenderer WaterDragonBodyDetails;
	private final ModelRenderer BodyD4_r1;
	private final ModelRenderer BodyD3_r1;

	public WaterDragonModel() {
		texWidth = 1024;
		texHeight = 1024;

		WaterDragonMain = new ModelRenderer(this);
		WaterDragonMain.setPos(0.0F, 24.0F, 532.0F);
		setRotationAngle(WaterDragonMain, 0.0F, 1.5708F, 0.0F);
		

		WaterDragonHead = new ModelRenderer(this);
		WaterDragonHead.setPos(0.0F, 0.0F, 0.0F);
		WaterDragonMain.addChild(WaterDragonHead);
		WaterDragonHead.texOffs(98, 405).addBox(556.0F, -52.0F, -23.0F, 10.0F, 32.0F, 29.0F, 0.0F, false);
		WaterDragonHead.texOffs(143, 140).addBox(563.0F, -55.0F, -27.0F, 30.0F, 36.0F, 37.0F, 0.0F, false);
		WaterDragonHead.texOffs(67, 336).addBox(591.0F, -54.0F, -26.0F, 10.0F, 34.0F, 35.0F, 0.0F, false);

		UpperSnout = new ModelRenderer(this);
		UpperSnout.setPos(599.0F, -32.0F, -9.0F);
		WaterDragonHead.addChild(UpperSnout);
		setRotationAngle(UpperSnout, 0.0F, 0.0F, -0.0436F);
		UpperSnout.texOffs(397, 419).addBox(-5.0F, -17.0F, -13.0F, 16.0F, 17.0F, 27.0F, 0.0F, false);
		UpperSnout.texOffs(276, 423).addBox(10.0F, -14.0F, -11.0F, 11.0F, 14.0F, 23.0F, 0.0F, false);
		UpperSnout.texOffs(397, 392).addBox(19.0F, -10.0F, -8.0F, 26.0F, 10.0F, 17.0F, 0.0F, false);
		UpperSnout.texOffs(77, 181).addBox(36.0F, -11.0F, -9.0F, 10.0F, 11.0F, 19.0F, 0.0F, false);

		TopTeeth = new ModelRenderer(this);
		TopTeeth.setPos(-3.0F, 23.0F, 0.0F);
		UpperSnout.addChild(TopTeeth);
		setRotationAngle(TopTeeth, 0.0F, 0.0F, -0.3927F);
		

		Tooth23_r1 = new ModelRenderer(this);
		Tooth23_r1.setPos(71.0F, -21.0F, 11.0F);
		TopTeeth.addChild(Tooth23_r1);
		setRotationAngle(Tooth23_r1, 0.0F, 0.0F, 0.3927F);
		Tooth23_r1.texOffs(6, 42).addBox(-47.2602F, 18.198F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(14, 44).addBox(-50.2602F, 19.198F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(0, 54).addBox(-44.2602F, 22.198F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(14, 54).addBox(-44.2602F, 22.198F, -21.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(26, 8).addBox(-47.2602F, 22.198F, -23.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(8, 80).addBox(-50.2602F, 22.198F, -23.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(26, 54).addBox(-17.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(6, 58).addBox(-20.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(20, 58).addBox(-23.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(0, 62).addBox(-26.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(12, 62).addBox(-29.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(26, 62).addBox(-32.2602F, 22.198F, -18.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(6, 66).addBox(-32.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(18, 66).addBox(-29.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(26, 68).addBox(-26.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(0, 70).addBox(-23.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(12, 70).addBox(-20.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(20, 72).addBox(-17.2602F, 22.198F, -4.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(6, 74).addBox(-12.2602F, 22.198F, -8.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(14, 76).addBox(-12.2602F, 22.198F, -14.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(20, 80).addBox(-11.2602F, 22.198F, -11.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(26, 76).addBox(-14.2602F, 22.198F, -17.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r1.texOffs(0, 78).addBox(-14.2602F, 22.198F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		LowerSnout = new ModelRenderer(this);
		LowerSnout.setPos(595.0F, -19.0F, -9.0F);
		WaterDragonHead.addChild(LowerSnout);
		setRotationAngle(LowerSnout, 0.0F, 0.0F, 0.3927F);
		LowerSnout.texOffs(240, 140).addBox(-7.0F, -10.0F, -12.0F, 17.0F, 10.0F, 25.0F, 0.0F, false);
		LowerSnout.texOffs(418, 248).addBox(7.0F, -10.0F, -10.0F, 13.0F, 10.0F, 21.0F, 0.0F, false);
		LowerSnout.texOffs(183, 278).addBox(17.0F, -10.0F, -7.0F, 27.0F, 10.0F, 15.0F, 0.0F, false);

		BottomTeeth = new ModelRenderer(this);
		BottomTeeth.setPos(-1.0F, -6.0F, 0.0F);
		LowerSnout.addChild(BottomTeeth);
		setRotationAngle(BottomTeeth, 0.0F, 0.0F, -0.3927F);
		

		Tooth23_r2 = new ModelRenderer(this);
		Tooth23_r2.setPos(63.0F, -11.0F, 11.0F);
		BottomTeeth.addChild(Tooth23_r2);
		setRotationAngle(Tooth23_r2, 0.0F, 0.0F, 0.3927F);
		Tooth23_r2.texOffs(0, 0).addBox(-43.5F, 28.198F, -2.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(6, 6).addBox(-49.5F, 28.198F, -0.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(8, 50).addBox(-46.5F, 27.198F, -0.2F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(20, 50).addBox(-46.5F, 27.198F, -22.8F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(0, 12).addBox(-49.5F, 28.198F, -22.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(12, 0).addBox(-43.5F, 28.198F, -20.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(12, 12).addBox(-17.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(6, 18).addBox(-20.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(18, 6).addBox(-23.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(18, 18).addBox(-26.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(0, 24).addBox(-29.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(24, 0).addBox(-32.5F, 28.198F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(12, 24).addBox(-32.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(24, 12).addBox(-29.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(24, 24).addBox(-26.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(6, 30).addBox(-23.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(18, 30).addBox(-20.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(26, 32).addBox(-17.5F, 28.198F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(0, 36).addBox(-12.5F, 27.198F, -8.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(12, 36).addBox(-12.0F, 29.198F, -11.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(20, 38).addBox(-12.5F, 27.198F, -14.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(26, 44).addBox(-14.2602F, 27.198F, -16.8F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth23_r2.texOffs(0, 48).addBox(-14.2602F, 27.198F, -6.2F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		WaterDragonHeadDetails = new ModelRenderer(this);
		WaterDragonHeadDetails.setPos(0.0F, 0.0F, 0.0F);
		WaterDragonHead.addChild(WaterDragonHeadDetails);
		

		HeadD3_r1 = new ModelRenderer(this);
		HeadD3_r1.setPos(567.0F, -37.0F, 14.0F);
		WaterDragonHeadDetails.addChild(HeadD3_r1);
		setRotationAngle(HeadD3_r1, 0.0F, 0.2618F, 0.0F);
		HeadD3_r1.texOffs(412, 194).addBox(-35.0F, -18.0F, 0.0F, 50.0F, 36.0F, 0.0F, 0.0F, false);

		HeadD2_r1 = new ModelRenderer(this);
		HeadD2_r1.setPos(567.0F, -37.0F, -31.0F);
		WaterDragonHeadDetails.addChild(HeadD2_r1);
		setRotationAngle(HeadD2_r1, 0.0F, -0.2618F, 0.0F);
		HeadD2_r1.texOffs(176, 419).addBox(-35.0F, -18.0F, 0.0F, 50.0F, 36.0F, 0.0F, 0.0F, false);

		HeadD1_r1 = new ModelRenderer(this);
		HeadD1_r1.setPos(562.0F, -62.0F, -8.5F);
		WaterDragonHeadDetails.addChild(HeadD1_r1);
		setRotationAngle(HeadD1_r1, 0.0F, 0.0F, 0.3491F);
		HeadD1_r1.texOffs(0, 140).addBox(-30.0F, 0.0F, -20.5F, 51.0F, 0.0F, 41.0F, 0.0F, false);

		WaterDragonBody = new ModelRenderer(this);
		WaterDragonBody.setPos(0.0F, 0.0F, 0.0F);
		WaterDragonMain.addChild(WaterDragonBody);
		WaterDragonBody.texOffs(0, 405).addBox(536.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(326, 392).addBox(516.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(255, 365).addBox(496.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(157, 365).addBox(476.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(360, 338).addBox(456.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(358, 280).addBox(436.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(341, 215).addBox(417.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(337, 140).addBox(397.0F, -49.0F, -22.0F, 22.0F, 27.0F, 27.0F, 0.0F, false);
		WaterDragonBody.texOffs(0, 311).addBox(377.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(285, 307).addBox(357.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(183, 307).addBox(337.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(110, 278).addBox(317.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(268, 249).addBox(298.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(264, 184).addBox(278.0F, -50.0F, -23.0F, 22.0F, 29.0F, 29.0F, 0.0F, false);
		WaterDragonBody.texOffs(0, 246).addBox(259.0F, -51.0F, -25.0F, 22.0F, 32.0F, 33.0F, 0.0F, false);
		WaterDragonBody.texOffs(187, 213).addBox(239.0F, -51.0F, -25.0F, 22.0F, 32.0F, 33.0F, 0.0F, false);
		WaterDragonBody.texOffs(77, 213).addBox(219.0F, -51.0F, -25.0F, 22.0F, 32.0F, 33.0F, 0.0F, false);
		WaterDragonBody.texOffs(0, 181).addBox(199.0F, -51.0F, -25.0F, 22.0F, 32.0F, 33.0F, 0.0F, false);

		WaterDragonBodyDetails = new ModelRenderer(this);
		WaterDragonBodyDetails.setPos(0.0F, 0.0F, 0.0F);
		WaterDragonBody.addChild(WaterDragonBodyDetails);
		WaterDragonBodyDetails.texOffs(0, 105).addBox(172.0F, -53.0F, -26.0F, 377.0F, 0.0F, 35.0F, 0.0F, false);
		WaterDragonBodyDetails.texOffs(0, 70).addBox(172.0F, -18.0F, -26.0F, 377.0F, 0.0F, 35.0F, 0.0F, false);

		BodyD4_r1 = new ModelRenderer(this);
		BodyD4_r1.setPos(0.0F, -44.0F, 60.0F);
		WaterDragonBodyDetails.addChild(BodyD4_r1);
		setRotationAngle(BodyD4_r1, 1.5708F, 0.0F, 0.0F);
		BodyD4_r1.texOffs(0, 0).addBox(172.0F, -51.0F, -26.0F, 377.0F, 0.0F, 35.0F, 0.0F, false);

		BodyD3_r1 = new ModelRenderer(this);
		BodyD3_r1.setPos(0.0F, -44.0F, 25.0F);
		WaterDragonBodyDetails.addChild(BodyD3_r1);
		setRotationAngle(BodyD3_r1, 1.5708F, 0.0F, 0.0F);
		BodyD3_r1.texOffs(0, 35).addBox(172.0F, -51.0F, -26.0F, 377.0F, 0.0F, 35.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		WaterDragonMain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}