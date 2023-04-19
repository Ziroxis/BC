package com.yuanno.block_clover.models;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.FlameBoarEntity;
import com.yuanno.block_clover.entities.MonkeyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class FlameBoarModel<T extends FlameBoarEntity> extends EntityModel<T> {
	private final ModelRenderer Flame_Boar;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer Flame_Tail;

	public FlameBoarModel() {
		texWidth = 128;
		texHeight = 128;

		Flame_Boar = new ModelRenderer(this);
		Flame_Boar.setPos(0.0F, 4.0F, 2.0F);


		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		Flame_Boar.addChild(body);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.texOffs(0, 0).addBox(-9.0F, -10.0F, -7.0F, 18.0F, 16.0F, 14.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-2.0F, -7.0F, 7.0F, 4.0F, 13.0F, 1.0F, 0.0F, false);
		body.texOffs(31, 36).addBox(-2.0F, 6.0F, -6.0F, 4.0F, 1.0F, 13.0F, 0.0F, false);
		body.texOffs(50, 0).addBox(-1.0F, 7.0F, -4.0F, 2.0F, 1.0F, 11.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -5.0F, -9.0F);
		Flame_Boar.addChild(head);
		head.texOffs(0, 30).addBox(-6.0F, -4.0F, -9.0F, 12.0F, 9.0F, 10.0F, 0.0F, false);
		head.texOffs(64, 12).addBox(-3.0F, 0.0F, -13.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		head.texOffs(65, 0).addBox(-10.0F, -2.0F, -8.0F, 4.0F, 1.0F, 5.0F, 0.0F, false);
		head.texOffs(17, 49).addBox(6.0F, -2.0F, -8.0F, 4.0F, 1.0F, 5.0F, 0.0F, false);
		head.texOffs(50, 4).addBox(-11.0F, -2.0F, -7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		head.texOffs(50, 0).addBox(10.0F, -2.0F, -7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		head.texOffs(64, 22).addBox(-2.0F, -5.0F, -6.0F, 4.0F, 1.0F, 7.0F, 0.0F, false);
		head.texOffs(34, 36).addBox(-2.0F, -4.0F, 1.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(20, 63).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		head.texOffs(0, 49).addBox(-1.0F, -4.0F, 2.0F, 2.0F, 1.0F, 13.0F, 0.0F, false);
		head.texOffs(0, 49).addBox(-4.0F, -1.0F, -13.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 30).addBox(3.0F, -1.0F, -13.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 37).addBox(-3.0F, 2.0F, -14.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(47, 30).addBox(1.0F, 2.0F, -14.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg0 = new ModelRenderer(this);
		leg0.setPos(-3.0F, 14.0F, 5.0F);
		Flame_Boar.addChild(leg0);
		leg0.texOffs(0, 63).addBox(-4.0F, -7.0F, -3.0F, 5.0F, 13.0F, 5.0F, 0.0F, false);
		leg0.texOffs(34, 68).addBox(-4.0F, 4.0F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(3.0F, 14.0F, 5.0F);
		Flame_Boar.addChild(leg1);
		leg1.texOffs(52, 30).addBox(-1.0F, -7.0F, -3.0F, 5.0F, 13.0F, 5.0F, 0.0F, false);
		leg1.texOffs(67, 30).addBox(-1.0F, 4.0F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-3.0F, 14.0F, -7.0F);
		Flame_Boar.addChild(leg2);
		leg2.texOffs(50, 50).addBox(-4.0F, -7.0F, -3.0F, 5.0F, 13.0F, 5.0F, 0.0F, false);
		leg2.texOffs(65, 6).addBox(-4.0F, 4.0F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(3.0F, 14.0F, -7.0F);
		Flame_Boar.addChild(leg3);
		leg3.texOffs(30, 50).addBox(-1.0F, -7.0F, -3.0F, 5.0F, 13.0F, 5.0F, 0.0F, false);
		leg3.texOffs(65, 48).addBox(-1.0F, 4.0F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		Flame_Tail = new ModelRenderer(this);
		Flame_Tail.setPos(0.0F, -2.0F, 6.0F);
		Flame_Boar.addChild(Flame_Tail);
		Flame_Tail.texOffs(63, 61).addBox(-3.0F, 6.0F, 1.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);
		Flame_Tail.texOffs(34, 30).addBox(-2.0F, 5.0F, 2.0F, 4.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Flame_Boar.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}