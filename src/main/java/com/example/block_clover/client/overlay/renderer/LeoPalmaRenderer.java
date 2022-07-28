package com.example.block_clover.client.overlay.renderer;

import com.example.block_clover.Main;
import com.example.block_clover.init.ModEffects;
import com.example.block_clover.models.projectiles.fire.LeoPalmaModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("unchecked")
public class LeoPalmaRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation resourceLocation =
            new ResourceLocation(Main.MODID + ":textures/entities/layers/spells/leopalma.png");

    private LeoPalmaModel model = new LeoPalmaModel();

    public LeoPalmaRenderer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (!(entitylivingbaseIn instanceof LivingEntity))
            return;

        LivingEntity target = (LivingEntity) entitylivingbaseIn;
        if (target.hasEffect(ModEffects.LEO_PALMA.get()))
        {
            matrixStackIn.pushPose();
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.setupAnim(target, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(bufferIn, this.model.renderType(resourceLocation), false, false);
            this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
    }
}
