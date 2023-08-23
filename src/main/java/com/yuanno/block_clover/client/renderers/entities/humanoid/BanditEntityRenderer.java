package com.yuanno.block_clover.client.renderers.entities.humanoid;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.humanoid.BanditEntity;
import com.yuanno.block_clover.models.entities.npc.BanditModel;
import com.yuanno.block_clover.models.entities.npc.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BanditEntityRenderer extends MobRenderer<BanditEntity, BanditModel<BanditEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/bandit.png");

    public BanditEntityRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new BanditModel<>(), 0.1F);
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
