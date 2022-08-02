package com.yuanno.block_clover.models.projectiles.lightning;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ThunderOrbModel extends EntityModel {

    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r1;
    public ThunderOrbModel() {
        texWidth = 32;
        texHeight = 32;
        bb_main = new ModelRenderer(this);
        bb_main.setPos(0.0F, 24.0F, 0.0F);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0F, -8.0F, 0.0F);
        bb_main.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.7854F, -0.7854F, 0.7854F);
        cube_r1.texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(Entity e, float f, float f1, float f2, float f3, float f4) {
    }
}

