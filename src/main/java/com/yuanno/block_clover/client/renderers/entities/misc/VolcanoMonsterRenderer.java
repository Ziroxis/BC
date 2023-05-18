package com.yuanno.block_clover.client.renderers.entities.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.misc.VolcanoMonsterEntity;
import com.yuanno.block_clover.models.entities.misc.VolcanoMonsterModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class VolcanoMonsterRenderer extends MobRenderer<VolcanoMonsterEntity, VolcanoMonsterModel<VolcanoMonsterEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/volcanomonster.png");

    public VolcanoMonsterRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VolcanoMonsterModel(), 1f);
    }

    @Override
    public void render(VolcanoMonsterEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    public static class Factory implements IRenderFactory<VolcanoMonsterEntity> {

        @Override
        public EntityRenderer<? super VolcanoMonsterEntity> createRenderFor(EntityRendererManager manager) {
            return new VolcanoMonsterRenderer(manager);
        }
    }


    @Override
    public ResourceLocation getTextureLocation(VolcanoMonsterEntity entity) {
        return TEXTURE;
    }

}