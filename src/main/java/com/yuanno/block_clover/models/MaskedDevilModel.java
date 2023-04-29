package com.yuanno.block_clover.models;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MaskedDevilModel<T extends LivingEntity> extends BipedModel<T> {
	public MaskedDevilModel(float p_i1148_1_) {
		super(p_i1148_1_);
	}
	/*
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

	public MaskedDevilModel() {
		super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
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
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.Devil).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.crouching = entityIn.isCrouching();

		this.Devil.copyFrom(this.rightArm);

		if(!(entityIn instanceof PlayerEntity))
			return;

		AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity) entityIn;

		ArmPose mainHandPos = armPose(clientPlayer, Hand.MAIN_HAND);
		ArmPose offHandPos = armPose(clientPlayer, Hand.OFF_HAND);

		this.swimAmount = clientPlayer.getSwimAmount(ageInTicks);

		if (clientPlayer.getMainArm() == HandSide.RIGHT) {
			this.rightArmPose = mainHandPos;
			this.leftArmPose = offHandPos;
		} else {
			this.rightArmPose = offHandPos;
			this.leftArmPose = mainHandPos;
		}

		this.Devil.copyFrom(this.rightArm);
	}

	private static ArmPose armPose(AbstractClientPlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.isEmpty()) {
			return ArmPose.EMPTY;
		} else {
			if (player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() > 0) {
				UseAction useaction = itemstack.getUseAnimation();
				if (useaction == UseAction.BLOCK) {
					return ArmPose.BLOCK;
				}

				if (useaction == UseAction.BOW) {
					return ArmPose.BOW_AND_ARROW;
				}

				if (useaction == UseAction.SPEAR) {
					return ArmPose.THROW_SPEAR;
				}

				if (useaction == UseAction.CROSSBOW && hand == player.getUsedItemHand()) {
					return ArmPose.CROSSBOW_CHARGE;
				}
			} else if (!player.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
				return ArmPose.CROSSBOW_HOLD;
			}

			return ArmPose.ITEM;
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	 */
}