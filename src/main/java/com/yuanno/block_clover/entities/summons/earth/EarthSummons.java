package com.yuanno.block_clover.entities.summons.earth;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.renderers.summons.earth.EarthGolemRenderer;
import com.yuanno.block_clover.client.renderers.summons.earth.EarthMinionRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EarthSummons {


    public static final RegistryObject<EntityType<EarthMinionEntity>> EARTH_MINION = Beapi.registerEntityType("Earth Minion",
            () -> BeModApi.createEntityType(EarthMinionEntity::new)
                    .sized(1f, 2f)
                    .build(Main.MODID + ":earth_minion"));
    public static final RegistryObject<EntityType<EarthGolemEntity>> EARTH_GOLEM = Beapi.registerEntityType("Earth Golem",
            () -> BeModApi.createEntityType(EarthGolemEntity::new)
                    .sized(1.5f, 3f)
                    .build(Main.MODID + ":earth_golem"));

    /*
    public static final RegistryObject<EntityType<EarthMinionEntity>> EARTH_MINION = ENTITIES
            .register("earth_minion",
                    () -> EntityType.Builder.of(EarthMinionEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .fireImmune()
                            .build(new ResourceLocation(Main.MODID, "earth_minion").toString()));

     */

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EARTH_MINION.get(), new EarthMinionRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EARTH_GOLEM.get(), new EarthGolemRenderer.Factory());
    }
}
