package com.example.block_clover.models.projectiles.wind;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WindBladeModel extends EntityModel {

    private final ModelRenderer sword;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;

    public WindBladeModel() {
        texWidth = 16;
        texHeight = 16;
        sword = new ModelRenderer(this);
        sword.setPos(0.0F, 22.75F, 0.0F);
        sword.texOffs(0, 0).addBox(-1.0F, -6.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        sword.texOffs(0, 0).addBox(-1.0F, -14.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);
        sword.texOffs(0, 0).addBox(-1.0F, -14.85F, -0.91F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-0.5F, -14.5F, 0.5F);
        sword.addChild(cube_r1);
        cube_r1.texOffs(0, 0).addBox(-0.5F, -0.7F, -1.39F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(0, 0).addBox(-0.5F, 0.1F, -1.4F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-0.5F, -14.5F, 0.5F);
        sword.addChild(cube_r2);
        cube_r2.texOffs(0, 0).addBox(-0.5F, -0.6F, -0.31F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(-0.5F, -1.5F, 0.0F);
        sword.addChild(cube_r3);
        cube_r3.texOffs(0, 0).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        sword.render(matrixStack, buffer, packedLight, packedOverlay);
    }



}
