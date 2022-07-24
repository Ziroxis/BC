package com.example.block_clover.entities.projectiles.wind;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.models.projectiles.wind.WindBladeModel;
import com.example.block_clover.models.projectiles.wind.WindCrescentModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindProjectiles {

    public static final RegistryObject<EntityType<WindBladeProjectile>> WIND_BLADE = Beapi.registerEntityType("Wind Blade",
            () -> Beapi.createEntityType(WindBladeProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":wind_blade"));

    public static final RegistryObject<EntityType<WindCrescentProjectile>> WIND_CRESCENT = Beapi.registerEntityType("Wind Crescent",
            () -> Beapi.createEntityType(WindCrescentProjectile::new)
                    .sized(2f, 2f)
                    .build(Main.MODID + ":wind_crescent"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WIND_BLADE.get(), new AbilityProjectileRenderer.Factory(new WindBladeModel())
                .setTexture("wind", "windblade").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(WIND_CRESCENT.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("wind", "windblade").setScale(1));
    }
}
