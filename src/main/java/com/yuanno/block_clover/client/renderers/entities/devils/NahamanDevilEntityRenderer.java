package com.yuanno.block_clover.client.renderers.entities.devils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.models.entities.devils.NahamanDevilModel;
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
public class NahamanDevilEntityRenderer extends MobRenderer<NahamanDevilEntity, NahamanDevilModel<NahamanDevilEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/devils/nahamandevil_texture.png");

    public NahamanDevilEntityRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new NahamanDevilModel<>(), 1f);
    }

    @Override
    public void render(NahamanDevilEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<NahamanDevilEntity>
    {
        @Override
        public EntityRenderer<? super NahamanDevilEntity> createRenderFor(EntityRendererManager manager)
        {
            return new NahamanDevilEntityRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(NahamanDevilEntity entity)
    {
        return TEXTURE;
    }

}
