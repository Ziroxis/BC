package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.wind.PiercingTornadoModel;
import com.yuanno.block_clover.models.projectiles.wind.WindBladeModel;
import com.yuanno.block_clover.models.projectiles.wind.WindCrescentModel;
import com.yuanno.block_clover.models.projectiles.wind.WindGaleModel;
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
    public static final RegistryObject<EntityType<WindGaleProjectile>> WIND_GALE = Beapi.registerEntityType("Wind Gale",
            () -> Beapi.createEntityType(WindGaleProjectile::new)
                    .sized(2f, 2f)
                    .build(Main.MODID + ":wind_gale"));
    public static final RegistryObject<EntityType<PiercingTornadoProjectile>> PIERCING_TORNADO = Beapi.registerEntityType("Piercing Tornado",
            () -> Beapi.createEntityType(PiercingTornadoProjectile::new)
                    .sized(1.5f, 1.5f)
                    .build(Main.MODID + ":piercing_tornado"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WIND_BLADE.get(), new AbilityProjectileRenderer.Factory(new WindBladeModel())
                .setTexture("wind", "windblade").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(WIND_CRESCENT.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("wind", "windblade").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(WIND_GALE.get(), new AbilityProjectileRenderer.Factory(new WindGaleModel())
                .setTexture("wind", "wind_gale").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(PIERCING_TORNADO.get(), new AbilityProjectileRenderer.Factory(new PiercingTornadoModel())
                .setTexture("wind", "tornado_piercing_blast").setScale(1));

    }
}
