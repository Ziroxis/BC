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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RhinocerosModel<T extends LivingEntity> extends BipedModel<T> {
    private final ModelRenderer Rhinocerous_Armor;
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;

    public RhinocerosModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 128;
        texHeight = 128;

        Rhinocerous_Armor = new ModelRenderer(this);
        Rhinocerous_Armor.setPos(0.0F, 24.0F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setPos(0.0F, -24.0F, 0.0F);
        Rhinocerous_Armor.addChild(Head);
        Head.texOffs(0, 0).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 8.0F, 10.0F, 0.0F, false);
        Head.texOffs(32, 0).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 8.0F, 10.0F, 0.5F, false);
        Head.texOffs(0, 0).addBox(-2.0F, -7.0F, -7.0F, 3.0F, 7.0F, 2.0F, 0.0F, false);
        Head.texOffs(0, 36).addBox(1.0F, -5.0F, -6.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
        Head.texOffs(0, 18).addBox(-4.0F, -5.0F, -6.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
        Head.texOffs(44, 30).addBox(-4.0F, -11.0F, -3.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        Head.texOffs(44, 7).addBox(1.0F, -11.0F, -3.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        Head.texOffs(18, 36).addBox(-6.0F, -2.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Head.texOffs(26, 18).addBox(3.0F, -2.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setPos(0.0F, -24.0F, 0.0F);
        Rhinocerous_Armor.addChild(Body);
        Body.texOffs(0, 18).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 12.0F, 6.0F, 0.0F, false);
        Body.texOffs(16, 32).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 12.0F, 6.0F, 0.25F, false);
        Body.texOffs(24, 48).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 12.0F, 2.0F, 0.0F, false);
        Body.texOffs(38, 0).addBox(-3.0F, 2.0F, -7.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
        Body.texOffs(30, 0).addBox(1.0F, 2.0F, -7.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, -22.0F, 0.0F);
        Rhinocerous_Armor.addChild(RightArm);
        RightArm.texOffs(44, 42).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
        RightArm.texOffs(40, 32).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.25F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, -22.0F, 0.0F);
        Rhinocerous_Armor.addChild(LeftArm);
        LeftArm.texOffs(0, 36).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
        LeftArm.texOffs(48, 48).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.25F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-1.9F, -12.0F, 0.0F);
        Rhinocerous_Armor.addChild(RightLeg);
        RightLeg.texOffs(34, 12).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
        RightLeg.texOffs(0, 32).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.25F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(1.9F, -12.0F, 0.0F);
        Rhinocerous_Armor.addChild(LeftLeg);
        LeftLeg.texOffs(26, 30).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
        LeftLeg.texOffs(0, 48).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.25F, false);    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Head, this.LeftArm, this.RightArm, this.Body, this.LeftLeg, this.RightLeg).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.crouching = entityIn.isCrouching();

        this.Head.copyFrom(this.head);
        this.Body.copyFrom(this.body);
        this.LeftArm.copyFrom(this.leftArm);
        this.RightArm.copyFrom(this.rightArm);
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

        this.Head.copyFrom(this.head);
        this.Body.copyFrom(this.body);
        this.LeftArm.copyFrom(this.leftArm);
        this.RightArm.copyFrom(this.rightArm);
        this.LeftLeg.copyFrom(this.leftLeg);
        this.RightLeg.copyFrom(this.rightLeg);
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
