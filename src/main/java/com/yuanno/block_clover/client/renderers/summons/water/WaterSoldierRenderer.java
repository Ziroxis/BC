package com.yuanno.block_clover.client.renderers.summons.water;

import com.yuanno.block_clover.HumanoidRenderer;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.summons.water.WaterSoldierEntity;
import com.yuanno.block_clover.models.entities.npc.HumanoidModel;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class WaterSoldierRenderer extends HumanoidRenderer<WaterSoldierEntity, BipedModel<WaterSoldierEntity>> {
    
    public WaterSoldierRenderer(EntityRendererManager manager)
    {
        super(manager, new HumanoidModel());
    }

    @Override
    public ResourceLocation getTextureLocation(WaterSoldierEntity entity)
    {
        return new ResourceLocation(Main.MODID, "entities/summons/water_soldier.png");
    }

    public static class Factory implements IRenderFactory<WaterSoldierEntity>
    {
        public Factory() {}

        @Override
        public EntityRenderer<? super WaterSoldierEntity> createRenderFor(EntityRendererManager manager)
        {
            return new WaterSoldierRenderer(manager);
        }
    }
}
