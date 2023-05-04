package com.yuanno.block_clover.client.renderers.entities.humanoid;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.humanoid.GrimoireMagicianEntity;
import com.yuanno.block_clover.models.entities.npc.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class GrimoireMagicianRenderer extends MobRenderer<GrimoireMagicianEntity, HumanoidModel<GrimoireMagicianEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/npc/grimoire_magician1.png");

    public GrimoireMagicianRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(GrimoireMagicianEntity p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<GrimoireMagicianEntity> {

        @Override
        public EntityRenderer<? super GrimoireMagicianEntity> createRenderFor(EntityRendererManager manager) {
            return new GrimoireMagicianRenderer(manager);
        }
    }
}
