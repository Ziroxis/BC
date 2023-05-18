package com.yuanno.block_clover.client.renderers.entities.beastial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.beastial.MonkeyEntity;
import com.yuanno.block_clover.models.entities.beastial.MonkeyModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class MonkeyEntityRenderer extends MobRenderer<MonkeyEntity, MonkeyModel<MonkeyEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/monkey_entity.png");

    public MonkeyEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MonkeyModel(), 1f);
    }

    @Override
    public void render(MonkeyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    public static class Factory implements IRenderFactory<MonkeyEntity> {

        @Override
        public EntityRenderer<? super MonkeyEntity> createRenderFor(EntityRendererManager manager) {
            return new MonkeyEntityRenderer(manager);
        }
    }


    @Override
    public ResourceLocation getTextureLocation(MonkeyEntity entity) {
        return TEXTURE;
    }
}
