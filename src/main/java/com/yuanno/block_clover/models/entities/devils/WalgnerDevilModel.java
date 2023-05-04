package com.yuanno.block_clover.models.entities.devils;// Made with Blockbench 4.2.4

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WalgnerDevilModel<T extends WalgnerDevilEntity> extends EntityModel<T> {

	private final ModelRenderer Devil;
	private final ModelRenderer Devil_Horn_Right;
	private final ModelRenderer head;
	private final ModelRenderer Body;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer Devil_Horn_Left;
	private final ModelRenderer Tail;

	public WalgnerDevilModel() {
		texWidth = 128;
		texHeight = 128;

		Devil = new ModelRenderer(this);
		Devil.setPos(-1.0F, 0.0F, 0.0F);
		

		Devil_Horn_Right = new ModelRenderer(this);
		Devil_Horn_Right.setPos(1.0F, 0.0F, 0.0F);
		Devil.addChild(Devil_Horn_Right);
		Devil_Horn_Right.texOffs(38, 28).addBox(-8.0F, -26.0F, -7.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Devil_Horn_Right.texOffs(38, 24).addBox(-6.0F, -25.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Devil_Horn_Right.texOffs(25, 36).addBox(-5.0F, -24.0F, -7.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(1.0F, 0.0F, 0.0F);
		Devil.addChild(head);
		head.texOffs(38, 0).addBox(-6.0F, -22.0F, -8.0F, 11.0F, 7.0F, 5.0F, -0.5F, false);
		head.texOffs(37, 62).addBox(-3.0F, -16.0F, -7.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setPos(1.0F, -14.0F, 0.0F);
		Devil.addChild(Body);
		Body.texOffs(0, 0).addBox(-6.0F, -10.0F, -4.0F, 11.0F, 28.0F, 8.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-2.0F, -12.0F, 0.0F);
		Devil.addChild(RightArm);
		RightArm.texOffs(51, 12).addBox(-7.0F, -2.0F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
		RightArm.texOffs(13, 36).addBox(-7.0F, 19.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(6.0F, -12.0F, 0.0F);
		Devil.addChild(LeftArm);
		LeftArm.texOffs(51, 37).addBox(0.0F, -2.0F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
		LeftArm.texOffs(30, 0).addBox(0.0F, 19.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-1.0F, -2.0F, 0.0F);
		Devil.addChild(RightLeg);
		RightLeg.texOffs(0, 36).addBox(-3.0F, 0.0F, -3.0F, 4.0F, 26.0F, 5.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(3.0F, -2.0F, 0.0F);
		Devil.addChild(LeftLeg);
		LeftLeg.texOffs(33, 31).addBox(-1.0F, 0.0F, -3.0F, 4.0F, 26.0F, 5.0F, 0.0F, false);

		Devil_Horn_Left = new ModelRenderer(this);
		Devil_Horn_Left.setPos(0.0F, 0.0F, 0.0F);
		Devil.addChild(Devil_Horn_Left);
		Devil_Horn_Left.texOffs(0, 5).addBox(6.0F, -26.0F, -7.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Devil_Horn_Left.texOffs(38, 20).addBox(4.0F, -25.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Devil_Horn_Left.texOffs(0, 0).addBox(3.0F, -24.0F, -7.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, 0.0F, 0.0F);
		Devil.addChild(Tail);
		Tail.texOffs(18, 53).addBox(-1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
		Tail.texOffs(38, 16).addBox(-1.0F, -4.0F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Tail.texOffs(38, 12).addBox(-1.0F, -6.0F, 14.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Tail.texOffs(30, 4).addBox(-1.0F, -8.0F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.RightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Devil.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}


}