package com.yuanno.block_clover.entities.summons.darkness;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.renderers.summons.darkness.BlackHoleRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DarknesSummons {

    public static final RegistryObject<EntityType<BlackHoleEntity>> BLACK_HOLE = Beapi.registerEntityType("Black Hole",
            () -> BeModApi.createEntityType(BlackHoleEntity::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":black_hole"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(BLACK_HOLE.get(), new BlackHoleRenderer.Factory());
    }


}
