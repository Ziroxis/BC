package com.example.block_clover.client.overlay.model;

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
public class EarthManipulationModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Earth_Manipulation;

    public EarthManipulationModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 64;
        texHeight = 64;

        Earth_Manipulation = new ModelRenderer(this);
        Earth_Manipulation.setPos(0.0F, 24.0F, 0.0F);
        Earth_Manipulation.texOffs(0, 0).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
        Earth_Manipulation.texOffs(0, 18).addBox(-7.0F, 2.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
    }


    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entityIn.isCrouching();

        /*
        this.Earth_Manipulation.copyFrom(this.rightArm);
        this.Earth_Manipulation.copyFrom(this.leftArm);
        this.Earth_Manipulation.copyFrom(this.leftLeg);
        this.Earth_Manipulation.copyFrom(this.rightLeg);

         */


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

        /*
        this.Earth_Manipulation.copyFrom(this.rightArm);
        this.Earth_Manipulation.copyFrom(this.leftArm);
        this.Earth_Manipulation.copyFrom(this.leftLeg);
        this.Earth_Manipulation.copyFrom(this.rightLeg);
        
         */
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Earth_Manipulation, this.Earth_Manipulation).forEach((modelRenderer) -> {
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
