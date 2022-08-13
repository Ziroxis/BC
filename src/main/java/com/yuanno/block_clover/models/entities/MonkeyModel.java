package com.yuanno.block_clover.models.entities;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.MonkeyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MonkeyModel<T extends MonkeyEntity> extends EntityModel<T> {
	private final ModelRenderer Monkey;
	private final ModelRenderer Tail;
	private final ModelRenderer Right_Arm2;
	private final ModelRenderer Head;
	private final ModelRenderer Right_Arm;
	private final ModelRenderer Chest;
	private final ModelRenderer Leg_Left;
	private final ModelRenderer Leg_Right;

	public MonkeyModel() {
		texWidth = 64;
		texHeight = 64;

		Monkey = new ModelRenderer(this);
		Monkey.setPos(0.0F, 4.0F, 0.0F);
		

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, 4.0F, 1.0F);
		Monkey.addChild(Tail);
		Tail.texOffs(17, 18).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 9.0F, 0.0F, false);
		Tail.texOffs(42, 21).addBox(-1.0F, -2.0F, 9.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Tail.texOffs(40, 18).addBox(-1.0F, -3.0F, 10.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Tail.texOffs(30, 23).addBox(-1.0F, -4.0F, 11.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Right_Arm2 = new ModelRenderer(this);
		Right_Arm2.setPos(8.0F, -4.0F, 0.0F);
		Monkey.addChild(Right_Arm2);
		Right_Arm2.texOffs(0, 27).addBox(0.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		Right_Arm2.texOffs(34, 41).addBox(1.0F, 15.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -6.0F, 0.0F);
		Monkey.addChild(Head);
		Head.texOffs(0, 17).addBox(-5.0F, -6.0F, -2.0F, 9.0F, 6.0F, 4.0F, 0.0F, false);
		Head.texOffs(28, 41).addBox(-4.0F, -10.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		Head.texOffs(40, 39).addBox(2.0F, -10.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		Head.texOffs(40, 11).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		Head.texOffs(44, 9).addBox(-3.0F, -11.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Head.texOffs(42, 34).addBox(1.0F, -11.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		Right_Arm = new ModelRenderer(this);
		Right_Arm.setPos(-9.0F, -4.0F, 0.0F);
		Monkey.addChild(Right_Arm);
		Right_Arm.texOffs(8, 27).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		Right_Arm.texOffs(42, 30).addBox(-1.0F, 15.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setPos(0.0F, -1.0F, 0.0F);
		Monkey.addChild(Chest);
		Chest.texOffs(0, 0).addBox(-8.0F, -5.0F, -2.0F, 16.0F, 13.0F, 4.0F, 0.0F, false);
		Chest.texOffs(22, 41).addBox(-7.0F, -4.0F, -3.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
		Chest.texOffs(16, 41).addBox(5.0F, -4.0F, -3.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
		Chest.texOffs(40, 0).addBox(5.0F, -4.0F, 2.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
		Chest.texOffs(36, 30).addBox(-7.0F, -4.0F, 2.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);

		Leg_Left = new ModelRenderer(this);
		Leg_Left.setPos(6.0F, 7.0F, 0.0F);
		Monkey.addChild(Leg_Left);
		Leg_Left.texOffs(30, 17).addBox(-1.0F, 11.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
		Leg_Left.texOffs(16, 28).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.0F, false);

		Leg_Right = new ModelRenderer(this);
		Leg_Right.setPos(-6.0F, 7.0F, 0.0F);
		Monkey.addChild(Leg_Right);
		Leg_Right.texOffs(26, 28).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.0F, false);
		Leg_Right.texOffs(35, 24).addBox(-2.0F, 11.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.xRot = headPitch * ((float)Math.PI / 180F);
		this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.Right_Arm2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.Right_Arm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.Leg_Right.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.Leg_Left.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Monkey.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}