package com.example.block_clover.models.projectiles.wind;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WindCrescentModel extends EntityModel {

    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;

    public WindCrescentModel() {
        texWidth = 16;
        texHeight = 16;

        bone = new ModelRenderer(this);
        bone.setPos(-0.5F, 13.256F, -0.1F);
        bone.texOffs(0, 0).addBox(-0.5F, -10.256F, -9.4F, 1.0F, 20.0F, 5.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0F, 4.244F, -5.4F);
        bone.addChild(cube_r1);
        cube_r1.texOffs(0, 0).addBox(-0.5F, -31.2319F, -9.6F, 1.0F, 20.0F, 5.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, 5.244F, -5.4F);
        bone.addChild(cube_r2);
        cube_r2.texOffs(0, 0).addBox(-0.5F, 2.5F, -5.5F, 1.0F, 20.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}
