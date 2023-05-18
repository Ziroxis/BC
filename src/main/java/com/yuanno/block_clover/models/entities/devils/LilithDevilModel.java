package com.yuanno.block_clover.models.entities.devils;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LilithDevilModel<T extends LilithDevilEntity> extends EntityModel<T> {
	private final ModelRenderer Lilith_Devil;
	private final ModelRenderer Head;
	private final ModelRenderer Half_Face_Right;
	private final ModelRenderer Devil_Horn_Bigger_Right;
	private final ModelRenderer Devil_Horn_Bigger_Right2;
	private final ModelRenderer Body;
	private final ModelRenderer Right_Wing;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer Hovering_Dress_Gown_Legs;
	private final ModelRenderer Left_Wing2;

	public LilithDevilModel() {
		texWidth = 64;
		texHeight = 64;

		Lilith_Devil = new ModelRenderer(this);
		Lilith_Devil.setPos(20.0F, -3.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setPos(-20.0F, -1.0F, 0.0F);
		Lilith_Devil.addChild(Head);
		Head.texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		Head.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		Half_Face_Right = new ModelRenderer(this);
		Half_Face_Right.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Half_Face_Right);
		Half_Face_Right.texOffs(14, 34).addBox(-4.0F, -9.0F, -5.0F, 4.0F, 7.0F, 1.0F, 0.0F, false);

		Devil_Horn_Bigger_Right = new ModelRenderer(this);
		Devil_Horn_Bigger_Right.setPos(-2.0F, 1.0F, -2.0F);
		Half_Face_Right.addChild(Devil_Horn_Bigger_Right);
		Devil_Horn_Bigger_Right.texOffs(0, 0).addBox(-4.0F, -12.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		Devil_Horn_Bigger_Right.texOffs(44, 50).addBox(-5.0F, -14.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Devil_Horn_Bigger_Right.texOffs(14, 50).addBox(-4.0F, -16.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		Devil_Horn_Bigger_Right2 = new ModelRenderer(this);
		Devil_Horn_Bigger_Right2.setPos(7.0F, 1.0F, -2.0F);
		Half_Face_Right.addChild(Devil_Horn_Bigger_Right2);
		Devil_Horn_Bigger_Right2.texOffs(14, 46).addBox(-3.0F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Devil_Horn_Bigger_Right2.texOffs(48, 3).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Devil_Horn_Bigger_Right2.texOffs(0, 5).addBox(-3.0F, -13.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setPos(-20.0F, 0.0F, 0.0F);
		Lilith_Devil.addChild(Body);
		Body.texOffs(28, 12).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 14.0F, 4.0F, 0.0F, false);
		Body.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		Right_Wing = new ModelRenderer(this);
		Right_Wing.setPos(-20.0F, 0.0F, 0.0F);
		Lilith_Devil.addChild(Right_Wing);
		Right_Wing.texOffs(44, 6).addBox(-6.0F, 4.0F, 2.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		Right_Wing.texOffs(36, 0).addBox(-16.0F, 4.0F, 2.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		Right_Wing.texOffs(44, 45).addBox(-11.0F, 2.0F, 2.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);
		Right_Wing.texOffs(44, 40).addBox(-21.0F, 3.0F, 2.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-25.0F, 0.0F, 0.0F);
		Lilith_Devil.addChild(RightArm);
		RightArm.texOffs(24, 30).addBox(-6.0F, -2.0F, -2.0F, 6.0F, 19.0F, 4.0F, 0.0F, false);
		RightArm.texOffs(40, 32).addBox(-6.0F, -2.0F, -2.0F, 5.0F, 19.0F, 4.0F, 0.25F, false);
		RightArm.texOffs(14, 42).addBox(-4.0F, 17.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		RightArm.texOffs(48, 0).addBox(-5.0F, -3.0F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(-15.0F, 1.0F, 0.0F);
		Lilith_Devil.addChild(LeftArm);
		LeftArm.texOffs(0, 34).addBox(0.0F, -3.0F, -2.0F, 3.0F, 17.0F, 4.0F, 0.0F, false);
		LeftArm.texOffs(48, 48).addBox(1.0F, -1.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.25F, false);

		Hovering_Dress_Gown_Legs = new ModelRenderer(this);
		Hovering_Dress_Gown_Legs.setPos(-17.1F, 14.0F, 0.0F);
		Lilith_Devil.addChild(Hovering_Dress_Gown_Legs);
		Hovering_Dress_Gown_Legs.texOffs(0, 16).addBox(-8.0F, -2.0F, -2.0F, 10.0F, 14.0F, 4.0F, 0.0F, false);
		Hovering_Dress_Gown_Legs.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		Left_Wing2 = new ModelRenderer(this);
		Left_Wing2.setPos(0.0F, 0.0F, 0.0F);
		Lilith_Devil.addChild(Left_Wing2);
		Left_Wing2.texOffs(32, 6).addBox(-11.0F, 3.0F, 2.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		Left_Wing2.texOffs(24, 0).addBox(-21.0F, 4.0F, 2.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		Left_Wing2.texOffs(44, 35).addBox(-6.0F, 2.0F, 2.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);
		Left_Wing2.texOffs(44, 30).addBox(-16.0F, 2.0F, 2.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.xRot = headPitch * ((float)Math.PI / 180F);
		this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.RightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		//this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		//this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Lilith_Devil.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}