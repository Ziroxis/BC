package com.example.block_clover.client;

import com.example.block_clover.Main;
import com.example.block_clover.entities.BanditEntity;
import com.example.block_clover.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BanditEntityRenderer extends MobRenderer<BanditEntity, HumanoidModel<BanditEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/bandit.png");

    public BanditEntityRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(BanditEntity p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<BanditEntity> {

        @Override
        public EntityRenderer<? super BanditEntity> createRenderFor(EntityRendererManager manager) {
            return new BanditEntityRenderer(manager);
        }
    }
}
