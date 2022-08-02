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

public class EarthGlovesModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Earth_Hands;
    
    public EarthGlovesModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 32;
        texHeight = 32;

        Earth_Hands = new ModelRenderer(this);
        Earth_Hands.setPos(-5.0F, 9.0F, 0.0F);
        Earth_Hands.texOffs(10, 11).addBox(-3.0F, -2.0F, -3.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);
        Earth_Hands.texOffs(10, 5).addBox(-3.0F, -2.0F, 2.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);
        Earth_Hands.texOffs(0, 5).addBox(-4.0F, -2.0F, -2.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);
        Earth_Hands.texOffs(0, 0).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entityIn.isCrouching();

        this.Earth_Hands.copyFrom(this.rightArm);
        this.Earth_Hands.copyFrom(this.leftArm);

        if(!(entityIn instanceof PlayerEntity))
            return;

        AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity) entityIn;

        BipedModel.ArmPose mainHandPos = armPose(clientPlayer, Hand.MAIN_HAND);
        BipedModel.ArmPose offHandPos = armPose(clientPlayer, Hand.OFF_HAND);

        this.swimAmount = clientPlayer.getSwimAmount(ageInTicks);

        if (clientPlayer.getMainArm() == HandSide.RIGHT) {
            this.rightArmPose = mainHandPos;
            this.leftArmPose = offHandPos;
        } else {
            this.rightArmPose = offHandPos;
            this.leftArmPose = mainHandPos;
        }

        this.Earth_Hands.copyFrom(this.rightArm);
        this.Earth_Hands.copyFrom(this.leftArm);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Earth_Hands, this.Earth_Hands).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    private static BipedModel.ArmPose armPose(AbstractClientPlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            return BipedModel.ArmPose.EMPTY;
        } else {
            if (player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() > 0) {
                UseAction useaction = itemstack.getUseAnimation();
                if (useaction == UseAction.BLOCK) {
                    return BipedModel.ArmPose.BLOCK;
                }

                if (useaction == UseAction.BOW) {
                    return BipedModel.ArmPose.BOW_AND_ARROW;
                }

                if (useaction == UseAction.SPEAR) {
                    return BipedModel.ArmPose.THROW_SPEAR;
                }

                if (useaction == UseAction.CROSSBOW && hand == player.getUsedItemHand()) {
                    return BipedModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!player.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return BipedModel.ArmPose.CROSSBOW_HOLD;
            }

            return BipedModel.ArmPose.ITEM;
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
