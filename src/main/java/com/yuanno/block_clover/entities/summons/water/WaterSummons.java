package com.yuanno.block_clover.entities.summons.water;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.renderers.summons.water.WaterShieldRenderer;
import com.yuanno.block_clover.client.renderers.summons.water.WaterSoldierRenderer;
import com.yuanno.block_clover.client.renderers.summons.water.WaterSubstituteRenderer;
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
            () -> BeModApi.createEntityType(WaterShieldEntity::new)
                    .sized(1.5f, 3f)
                    .build(Main.MODID + ":water_shield"));
    public static final RegistryObject<EntityType<WaterSubstituteEntity>> WATER_SUBSTITUTE = Beapi.registerEntityType("Water Substitute",
            () -> BeModApi.createEntityType(WaterSubstituteEntity::new)
                    .sized(0.8f, 1.8f)
                    .build(Main.MODID + ":water_substitute"));
    public static final RegistryObject<EntityType<WaterSoldierEntity>> WATER_SOLDIER = Beapi.registerEntityType("Water Soldier",
            () -> BeModApi.createEntityType(WaterSoldierEntity::new)
                    .sized(0.8f, 1.8f)
                    .build(Main.MODID + ":water_soldier"));
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WATER_SHIELD.get(), new WaterShieldRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(WATER_SUBSTITUTE.get(), new WaterSubstituteRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(WATER_SOLDIER.get(), new WaterSoldierRenderer.Factory());
    }
}
