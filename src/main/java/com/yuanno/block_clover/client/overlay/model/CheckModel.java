package com.yuanno.block_clover.client.overlay.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class CheckModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Complete_Overlay;

    public CheckModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 16;
        texHeight = 16;

        Complete_Overlay = new ModelRenderer(this);
        Complete_Overlay.setPos(0.0F, 0.0F, 0.0F);
        Complete_Overlay.texOffs(0, 8).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Complete_Overlay.texOffs(6, 6).addBox(-3.0F, -13.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Complete_Overlay.texOffs(6, 2).addBox(1.0F, -13.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Complete_Overlay.texOffs(0, 4).addBox(2.0F, -15.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Complete_Overlay.texOffs(0, 0).addBox(-4.0F, -15.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entityIn.isCrouching();

        this.body.y -= 20;
        this.Complete_Overlay.copyFrom(this.body);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Complete_Overlay).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }



    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
