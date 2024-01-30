package com.yuanno.block_clover.entities.summons.mercury;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.renderers.summons.mercury.MercuryBubbleRenderer;
import com.yuanno.block_clover.client.renderers.summons.water.WaterShieldRenderer;
import com.yuanno.block_clover.entities.summons.water.WaterShieldEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MercurySummons {

    public static final RegistryObject<EntityType<MercuryBubbleEntity>> MERCURY_BUBBLE = Beapi.registerEntityType("Mercury Bubble",
            () -> BeModApi.createEntityType(MercuryBubbleEntity::new)
                    .sized(1.5f, 3f)
                    .build(Main.MODID + ":mercury_build"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(MERCURY_BUBBLE.get(), new MercuryBubbleRenderer.Factory());

    }
}
