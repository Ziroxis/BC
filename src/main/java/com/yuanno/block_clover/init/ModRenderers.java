package com.yuanno.block_clover.init;

import com.yuanno.block_clover.HumanoidRenderer;
import com.yuanno.block_clover.client.overlay.renderer.ExclamationRenderer;
import com.yuanno.block_clover.models.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Map;

public class ModRenderers {

    public static void registerRenderers()
    {

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GRIMOIRE_MAGICIAN.get(), new HumanoidRenderer.Factory(new HumanoidModel(), 1));

        for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : Minecraft.getInstance().getEntityRenderDispatcher().renderers.entrySet())
        {
            EntityRenderer entityRenderer = entry.getValue();
            if (entityRenderer instanceof LivingRenderer)
            {
                LivingRenderer renderer = (LivingRenderer) entityRenderer;
                renderer.addLayer(new ExclamationRenderer(renderer));

            }
        }

        for (Map.Entry<String, PlayerRenderer> entry : Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().entrySet())
        {
            PlayerRenderer renderer = entry.getValue();
            renderer.addLayer(new ExclamationRenderer(renderer));

        }
    }
}
