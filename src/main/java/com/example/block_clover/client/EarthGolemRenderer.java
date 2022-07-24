package com.example.block_clover.client;

import com.example.block_clover.entities.summons.earth.EarthGolemEntity;
import com.example.block_clover.models.entities.summons.EarthGolemModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class EarthGolemRenderer extends MobRenderer<EarthGolemEntity, EarthGolemModel<EarthGolemEntity>> {

    private static final ResourceLocation texture = new ResourceLocation("block_clover:textures/entities/summons/earthgolem.png");

    public EarthGolemRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new EarthGolemModel<>(), 1f);
    }

    @Override
    public void render(EarthGolemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EarthGolemEntity entity) {
        return texture;
    }

    public static class Factory implements IRenderFactory<EarthGolemEntity> {

        @Override
        public EntityRenderer<? super EarthGolemEntity> createRenderFor(EntityRendererManager manager) {
            return new EarthGolemRenderer(manager);
        }
    }
}
