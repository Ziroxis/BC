package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.overlay.model.ManaSkinModel;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.init.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;


@OnlyIn(Dist.CLIENT)
public class BubbledRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
    float[] color = new float[] {0.0F, 0.44F, 0.53F, 0.09F};

    public BubbledRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight,
                       T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (entity.hasEffect(ModEffects.BUBBLED.get())) {
            matrixStack.pushPose();

            String color = "#006994";


            OutlineLayerBuffer outline = Minecraft.getInstance().renderBuffers().outlineBufferSource();
            Color rgbColor = Beapi.hexToRGB(color);
            float red = rgbColor.getRed() / 255.0f;
            float green = rgbColor.getGreen() / 255.0f;
            float blue = rgbColor.getBlue() / 255.0f;
            outline.setColor((int) (red * 255), (int) (green * 255), (int) (blue * 255), 200);
            IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(this.getTextureLocation(entity)));

            this.getParentModel().renderToBuffer(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6f);

            matrixStack.popPose();
        }
    }

    /*
    matrixStack.pushPose();

            String color = "#006994";


            OutlineLayerBuffer outline = Minecraft.getInstance().renderBuffers().outlineBufferSource();
            Color rgbColor = Beapi.hexToRGB(color);
            float red = rgbColor.getRed() / 255.0f;
            float green = rgbColor.getGreen() / 255.0f;
            float blue = rgbColor.getBlue() / 255.0f;
            outline.setColor((int)(red * 255), (int)(green * 255), (int)(blue * 255), 200);
            IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(this.getTextureLocation(entity)));

            this.getParentModel().renderToBuffer(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6f);

            matrixStack.popPose();
     */
}
