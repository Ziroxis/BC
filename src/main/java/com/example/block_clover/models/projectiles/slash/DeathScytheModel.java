package com.example.block_clover.models.projectiles.slash;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeathScytheModel extends EntityModel {

    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    public DeathScytheModel() {
        texWidth = 16;
        texHeight = 16;

        bone = new ModelRenderer(this);
        bone.setPos(-6.1294F, 19.983F, 0.5F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.6192F, -4.1326F, 0.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 1.5708F);
        cube_r1.texOffs(3, 4).addBox(-1.0F, -9.1F, -0.5F, 2.0F, 7.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(2.0743F, -3.339F, 0.0F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
        cube_r2.texOffs(3, 4).addBox(-0.7165F, -1.8F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(10.701F, -3.339F, 0.0F);
        bone.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.7854F);
        cube_r3.texOffs(3, 4).addBox(-1.5F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(12.2588F, 0.0F, 0.0F);
        bone.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.48F);
        cube_r4.texOffs(3, 4).addBox(-0.8F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(0.0F, 0.0F, 0.0F);
        bone.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, 0.48F);
        cube_r5.texOffs(3, 4).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}

