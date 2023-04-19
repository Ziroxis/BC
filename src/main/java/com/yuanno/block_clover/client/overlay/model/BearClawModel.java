package com.yuanno.block_clover.client.overlay.model;

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

public class BearClawModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer BearClaw;

    public BearClawModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 64;
        texHeight = 64;

        BearClaw = new ModelRenderer(this);
        BearClaw.setPos(5.0F, 2.0F, 0.0F);
        BearClaw.texOffs(22, 27).addBox(-1.0F, -2.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        BearClaw.texOffs(22, 14).addBox(-1.0F, -2.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        BearClaw.texOffs(8, 12).addBox(3.0F, -2.0F, -3.0F, 1.0F, 12.0F, 6.0F, 0.0F, false);
        BearClaw.texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 1.0F, 12.0F, 6.0F, 0.0F, false);
        BearClaw.texOffs(0, 24).addBox(-2.0F, 11.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        BearClaw.texOffs(8, 0).addBox(0.0F, 11.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        BearClaw.texOffs(0, 18).addBox(3.0F, 11.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        BearClaw.texOffs(16, 7).addBox(-2.0F, 10.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        BearClaw.texOffs(14, 0).addBox(-2.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);        }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entityIn.isCrouching();

        this.BearClaw.copyFrom(this.rightArm);
        this.BearClaw.copyFrom(this.leftArm);

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

        this.BearClaw.copyFrom(this.rightArm);
        this.BearClaw.copyFrom(this.leftArm);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.BearClaw, this.BearClaw).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
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
