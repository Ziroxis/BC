package com.yuanno.block_clover.client.renderers.entities.devils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.models.entities.devils.WalgnerDevilModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class WalgnerDevilEntityRenderer extends MobRenderer<WalgnerDevilEntity, WalgnerDevilModel<WalgnerDevilEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/devils/walgnerdevil_texture.png");

    public WalgnerDevilEntityRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new WalgnerDevilModel<>(), 1f);
    }

    @Override
    public void render(WalgnerDevilEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<WalgnerDevilEntity>
    {
        @Override
        public EntityRenderer<? super WalgnerDevilEntity> createRenderFor(EntityRendererManager manager)
        {
            return new WalgnerDevilEntityRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(WalgnerDevilEntity entity)
    {
        return TEXTURE;
    }
}
