package com.example.block_clover.models.projectiles.earth;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EarthChargeModel extends EntityModel {

    private final ModelRenderer Earth_Pillar_Charge;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;

    public EarthChargeModel()
    {
        texWidth = 256;
        texHeight = 256;

        Earth_Pillar_Charge = new ModelRenderer(this);
        Earth_Pillar_Charge.setPos(-1.0F, 15.0F, -4.0F);
        Earth_Pillar_Charge.texOffs(0, 0).addBox(-13.0F, -14.0F, -8.0F, 26.0F, 23.0F, 40.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(1.0F, 9.0F, 4.0F);
        Earth_Pillar_Charge.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.1745F, 0.0F);
        cube_r1.texOffs(75, 66).addBox(11.0F, -21.0F, -7.0F, 3.0F, 19.0F, 31.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(1.0F, 9.0F, 4.0F);
        Earth_Pillar_Charge.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.1309F, 0.0F);
        cube_r2.texOffs(0, 97).addBox(-17.0F, -21.0F, -7.0F, 3.0F, 19.0F, 31.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(1.0F, 9.0F, 4.0F);
        Earth_Pillar_Charge.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1175F, 0.0142F, -0.0019F);
        cube_r3.texOffs(0, 63).addBox(-12.0F, -26.0F, -8.0F, 22.0F, 3.0F, 31.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }


    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        Earth_Pillar_Charge.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
