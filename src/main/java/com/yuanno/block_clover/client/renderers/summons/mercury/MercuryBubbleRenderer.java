package com.yuanno.block_clover.client.renderers.summons.mercury;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.summons.mercury.MercuryBubbleEntity;
import com.yuanno.block_clover.entities.summons.water.WaterShieldEntity;
import com.yuanno.block_clover.models.entities.summons.mercury.MercuryBubbleModel;
import com.yuanno.block_clover.models.entities.summons.water.WaterShieldModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MercuryBubbleRenderer extends EntityRenderer<MercuryBubbleEntity> {

    private static final ResourceLocation texture = new ResourceLocation("block_clover:textures/entities/summons/mercurybubble_texture.png");

    public MercuryBubbleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(MercuryBubbleEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        IVertexBuilder vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
        EntityModel model = new MercuryBubbleModel();
        model.renderToBuffer(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static class Factory implements IRenderFactory<MercuryBubbleEntity> {

        @Override
        public EntityRenderer<? super MercuryBubbleEntity> createRenderFor(EntityRendererManager manager) {
            return new MercuryBubbleRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(MercuryBubbleEntity entity) {
        return texture;
    }

}
