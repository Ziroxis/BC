package com.yuanno.block_clover.client.renderers.entities.beastial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.beastial.CloverSharkEntity;
import com.yuanno.block_clover.models.entities.beastial.CloverSharkModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class CloverSharkRenderer extends MobRenderer<CloverSharkEntity, CloverSharkModel<CloverSharkEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/clovershark_texture.png");

    public CloverSharkRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new CloverSharkModel(), 1f);
    }

    @Override
    public void render(CloverSharkEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<CloverSharkEntity>
    {
        @Override
        public EntityRenderer<? super CloverSharkEntity> createRenderFor(EntityRendererManager manager)
        {
            return new CloverSharkRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(CloverSharkEntity entity)
    {
        return TEXTURE;
    }
}
