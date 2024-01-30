package com.yuanno.block_clover.models.entities.npc;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.humanoid.npc.BanditEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BanditModel<T extends BanditEntity> extends BipedModel<T> {
	private final ModelRenderer bone;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer bone2;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightArm;
	private final ModelRenderer Body;
	private final ModelRenderer Head;

	public BanditModel() {
		super(0, 0, 64, 32);
		texWidth = 96;
		texHeight = 96;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 5.0F, 0.0F);


		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(1.9F, 7.0F, 0.0F);
		bone.addChild(LeftLeg);
		LeftLeg.texOffs(0, 68).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		LeftLeg.texOffs(34, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		LeftLeg.texOffs(34, 68).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.35F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-1.9F, 7.0F, 0.0F);
		bone.addChild(RightLeg);
		RightLeg.texOffs(0, 34).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		RightLeg.texOffs(50, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		RightLeg.texOffs(17, 68).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.35F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(1.9F, 12.0F, 0.0F);
		RightLeg.addChild(bone2);
		bone2.texOffs(51, 68).addBox(-4.9F, -10.75F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
		bone2.texOffs(0, 85).addBox(-3.9F, -10.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.35F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(5.0F, -3.0F, 0.0F);
		bone.addChild(LeftArm);
		LeftArm.texOffs(17, 51).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
		LeftArm.texOffs(0, 51).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-5.0F, -3.0F, 0.0F);
		bone.addChild(RightArm);
		RightArm.texOffs(17, 51).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, true);
		RightArm.texOffs(0, 51).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, true);

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 7.0F, 0.0F);
		bone.addChild(Body);
		Body.texOffs(25, 17).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		Body.texOffs(0, 17).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -5.0F, 0.0F);
		bone.addChild(Head);
		Head.texOffs(33, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.Head.xRot = headPitch * ((float)Math.PI / 180F);
		this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.RightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@OnlyIn(Dist.CLIENT)
	public static enum ArmPose {
		EMPTY(false),
		ITEM(false),
		BLOCK(false),
		BOW_AND_ARROW(true),
		THROW_SPEAR(false),
		CROSSBOW_CHARGE(true),
		CROSSBOW_HOLD(true);

		private final boolean twoHanded;

		private ArmPose(boolean p_i241257_3_) {
			this.twoHanded = p_i241257_3_;
		}

		public boolean isTwoHanded() {
			return this.twoHanded;
		}
	}
}