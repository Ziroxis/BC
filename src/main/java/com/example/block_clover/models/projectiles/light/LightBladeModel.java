package com.example.block_clover.models.projectiles.light;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LightBladeModel extends EntityModel {


    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;

    public LightBladeModel() {
        texWidth = 48;
        texHeight = 48;

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 24.0F, 0.0F);
        setRotationAngle(bone, 0.0F, -1.5708F, 0.0F);
        bone.texOffs(0, 23).addBox(-1.0F, -12.5F, -1.0F, 2.0F, 7.0F, 2.0F, -0.5F, false);
        bone.texOffs(9, 23).addBox(-1.0F, -17.25F, -1.0F, 2.0F, 6.0F, 2.0F, -0.6F, false);
        bone.texOffs(18, 23).addBox(-1.0F, -20.75F, -1.0F, 2.0F, 5.0F, 2.0F, -0.7F, false);
        bone.texOffs(9, 32).addBox(-1.0F, -22.25F, -1.0F, 2.0F, 3.0F, 2.0F, -0.8F, false);
        bone.texOffs(0, 32).addBox(-1.0F, -7.25F, -1.0F, 2.0F, 4.0F, 2.0F, -0.6F, false);
        bone.texOffs(9, 38).addBox(-1.0F, -4.75F, -1.0F, 2.0F, 3.0F, 2.0F, -0.7F, false);
        bone.texOffs(0, 38).addBox(-1.0F, -3.25F, -1.0F, 2.0F, 2.0F, 2.0F, -0.8F, false);
        bone.texOffs(0, 0).addBox(-8.0F, -22.5F, -0.2F, 16.0F, 22.0F, 0.0F, -0.25F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0071F, -21.95F, 0.05F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 0.7854F);
        cube_r1.texOffs(18, 32).addBox(-0.75F, -0.75F, -1.05F, 2.0F, 2.0F, 2.0F, -0.75F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, -1.25F, 0.5F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
        cube_r2.texOffs(18, 38).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, -0.25F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
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

