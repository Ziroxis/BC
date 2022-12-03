package com.yuanno.block_clover.client.overlay.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ExclamationModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Exclamation_Mark_Top;

    public ExclamationModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 16;
        texHeight = 16;

        Exclamation_Mark_Top = new ModelRenderer(this);
        Exclamation_Mark_Top.setPos(0.0F, 20.0F, 0.0F);
        Exclamation_Mark_Top.texOffs(0, 0).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
        Exclamation_Mark_Top.texOffs(6, 0).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
    }
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entityIn.isCrouching();

        this.body.y -= 20;
        this.Exclamation_Mark_Top.copyFrom(this.body);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Exclamation_Mark_Top).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }



    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
