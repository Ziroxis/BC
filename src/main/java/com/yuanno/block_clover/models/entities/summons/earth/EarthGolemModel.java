package com.yuanno.block_clover.models.entities.summons.earth;

import com.yuanno.block_clover.entities.summons.earth.EarthGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EarthGolemModel<T extends EarthGolemEntity> extends EntityModel<T> {

    private final ModelRenderer EarthGolem;
    private final ModelRenderer Head;
    private final ModelRenderer Base_Body;
    private final ModelRenderer Arm_Right;
    private final ModelRenderer Arm_Left;
    private final ModelRenderer Leg_Left;
    private final ModelRenderer Leg_Right;

    public EarthGolemModel() {
        texWidth = 128;
        texHeight = 128;

        EarthGolem = new ModelRenderer(this);
        EarthGolem.setPos(0.0F, 24.0F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setPos(0.0F, -42.0F, 0.0F);
        EarthGolem.addChild(Head);
        Head.texOffs(48, 36).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 7.0F, 0.0F, false);

        Base_Body = new ModelRenderer(this);
        Base_Body.setPos(0.0F, -29.0F, 0.0F);
        EarthGolem.addChild(Base_Body);
        Base_Body.texOffs(0, 22).addBox(-9.0F, -13.0F, -4.0F, 18.0F, 12.0F, 9.0F, 0.0F, false);
        Base_Body.texOffs(0, 0).addBox(-10.0F, -1.0F, -5.0F, 20.0F, 11.0F, 11.0F, 0.0F, false);

        Arm_Right = new ModelRenderer(this);
        Arm_Right.setPos(-9.0F, -39.0F, 0.0F);
        EarthGolem.addChild(Arm_Right);
        Arm_Right.texOffs(56, 25).addBox(-3.0F, -2.0F, -2.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
        Arm_Right.texOffs(24, 43).addBox(-6.0F, -2.0F, -3.0F, 5.0F, 19.0F, 7.0F, 0.0F, false);

        Arm_Left = new ModelRenderer(this);
        Arm_Left.setPos(10.0F, -39.0F, 0.0F);
        EarthGolem.addChild(Arm_Left);
        Arm_Left.texOffs(0, 43).addBox(0.0F, -2.0F, -3.0F, 5.0F, 19.0F, 7.0F, 0.0F, false);
        Arm_Left.texOffs(45, 22).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

        Leg_Left = new ModelRenderer(this);
        Leg_Left.setPos(7.0F, -19.0F, 0.0F);
        EarthGolem.addChild(Leg_Left);
        Leg_Left.texOffs(62, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 19.0F, 5.0F, 0.0F, false);

        Leg_Right = new ModelRenderer(this);
        Leg_Right.setPos(-7.0F, -19.0F, 0.0F);
        EarthGolem.addChild(Leg_Right);
        Leg_Right.texOffs(48, 51).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 19.0F, 5.0F, 0.0F, false);
    }
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Head.xRot = headPitch * ((float)Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.Arm_Right.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.Arm_Left.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.Leg_Right.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.Leg_Left.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

    }


    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        EarthGolem.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
