package com.yuanno.block_clover.entities.summons.water;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.renderers.summons.water.WaterShieldRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WaterSummons {

    public static final RegistryObject<EntityType<WaterShieldEntity>> WATER_SHIELD = Beapi.registerEntityType("Water Shield",
            () -> Beapi.createEntityType(WaterShieldEntity::new)
                    .sized(1.5f, 3f)
                    .build(Main.MODID + ":water_shield"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WATER_SHIELD.get(), new WaterShieldRenderer.Factory());
    }
}
