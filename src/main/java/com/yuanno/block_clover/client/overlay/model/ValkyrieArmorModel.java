package com.yuanno.block_clover.client.overlay.model;// Made with Blockbench 4.2.4
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
public class ValkyrieArmorModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public ValkyrieArmorModel() {
		super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
		texWidth = 128;
		texHeight = 128;

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 0.0F, 0.0F);
		Head.texOffs(66, 29).addBox(-3.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Head.texOffs(66, 22).addBox(2.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Head.texOffs(68, 56).addBox(-2.0F, -9.0F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Head.texOffs(68, 54).addBox(-2.0F, -9.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Head.texOffs(66, 0).addBox(2.0F, -11.0F, -3.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(56, 54).addBox(-3.0F, -11.0F, -3.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(52, 54).addBox(-3.0F, -11.0F, 2.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(40, 54).addBox(2.0F, -11.0F, 2.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(36, 54).addBox(2.0F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(24, 54).addBox(-3.0F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(20, 54).addBox(-0.5F, -11.0F, 2.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(0, 54).addBox(-0.5F, -11.0F, -3.0F, 1.0F, 3.0F, 1.0F, -0.2F, false);
		Head.texOffs(66, 12).addBox(-3.0F, -11.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 29).addBox(-3.0F, -11.5F, -3.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 26).addBox(-3.0F, -11.5F, 2.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 24).addBox(2.0F, -11.5F, 2.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 22).addBox(2.0F, -11.5F, -3.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 19).addBox(-0.5F, -11.5F, -3.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 17).addBox(-0.5F, -11.5F, 2.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		Head.texOffs(66, 14).addBox(2.0F, -11.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 0.0F, 0.0F);
		Body.texOffs(0, 54).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 11.0F, 4.0F, 0.3F, false);
		Body.texOffs(66, 49).addBox(-4.0F, 0.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.29F, false);
		Body.texOffs(66, 44).addBox(2.0F, 0.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.29F, false);
		Body.texOffs(66, 40).addBox(-4.0F, 11.5F, -2.0F, 8.0F, 2.0F, 0.0F, 0.5F, false);
		Body.texOffs(66, 38).addBox(-4.0F, 11.5F, 2.0F, 8.0F, 2.0F, 0.0F, 0.5F, false);
		Body.texOffs(66, 66).addBox(-4.0F, 11.5F, -1.0F, 0.0F, 2.0F, 2.0F, 0.5F, false);
		Body.texOffs(58, 66).addBox(4.0F, 11.5F, -1.0F, 0.0F, 2.0F, 2.0F, 0.5F, false);
		Body.texOffs(0, 27).addBox(-33.0F, -17.0F, 2.4F, 33.0F, 27.0F, 0.0F, 0.0F, false);
		Body.texOffs(0, 0).addBox(0.0F, -17.0F, 2.4F, 33.0F, 27.0F, 0.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-5.0F, 2.0F, 0.0F);
		RightArm.texOffs(40, 54).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(5.0F, 2.0F, 0.0F);
		LeftArm.texOffs(24, 54).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-1.9F, 12.0F, 0.0F);
		RightLeg.texOffs(66, 0).addBox(-2.0F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.27F, false);
		RightLeg.texOffs(66, 12).addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.4F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(1.9F, 12.0F, 0.0F);
		LeftLeg.texOffs(56, 54).addBox(-2.0F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.27F, false);
		LeftLeg.texOffs(66, 17).addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.4F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg, this.Head, this.Body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}
	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.crouching = entityIn.isCrouching();

		this.RightArm.copyFrom(this.rightArm);
		this.LeftArm.copyFrom(this.leftArm);
		this.Head.copyFrom(this.head);
		this.Body.copyFrom(this.body);
		this.LeftLeg.copyFrom(this.leftLeg);
		this.RightLeg.copyFrom(this.rightLeg);

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

		this.RightArm.copyFrom(this.rightArm);
		this.LeftArm.copyFrom(this.leftArm);
		this.Head.copyFrom(this.head);
		this.LeftArm.copyFrom(this.leftArm);
		this.RightLeg.copyFrom(this.rightLeg);
		this.Body.copyFrom(this.body);

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
}