package com.yuanno.block_clover.client.renderers.entities.beastial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.beastial.FlameBoarEntity;
import com.yuanno.block_clover.models.entities.beastial.FlameBoarModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FlameBoarRenderer extends MobRenderer<FlameBoarEntity, FlameBoarModel<FlameBoarEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/flame_boar.png");

    public FlameBoarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlameBoarModel<>(), 1f);
    }

    @Override
    public void render(FlameBoarEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    public static class Factory implements IRenderFactory<FlameBoarEntity> {

        @Override
        public EntityRenderer<? super FlameBoarEntity> createRenderFor(EntityRendererManager manager) {
            return new FlameBoarRenderer(manager);
        }
    }


    @Override
    public ResourceLocation getTextureLocation(FlameBoarEntity entity) {
        return TEXTURE;
    }
}
