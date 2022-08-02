package com.yuanno.block_clover.client.renderers.summons.earth;

import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import com.yuanno.block_clover.models.entities.summons.earth.EarthMinionModel;
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
public class EarthMinionRenderer extends MobRenderer<EarthMinionEntity, EarthMinionModel<EarthMinionEntity>> {

    private static final ResourceLocation texture = new ResourceLocation("block_clover:textures/entities/summons/earthminion.png");

    public EarthMinionRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new EarthMinionModel<>(), 1f);
    }

    @Override
    public void render(EarthMinionEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EarthMinionEntity entity) {
        return texture;
    }

    public static class Factory implements IRenderFactory<EarthMinionEntity> {

        @Override
        public EntityRenderer<? super EarthMinionEntity> createRenderFor(EntityRendererManager manager) {
            return new EarthMinionRenderer(manager);
        }
    }
}
