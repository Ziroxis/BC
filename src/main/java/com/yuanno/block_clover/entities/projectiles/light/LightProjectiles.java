package com.yuanno.block_clover.entities.projectiles.light;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.light.LightBladeModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LightProjectiles {

    public static final RegistryObject<EntityType<LightBladeProjectile>> LIGHT_BLADE = Beapi.registerEntityType("Light Blade",
            () -> BeModApi.createEntityType(LightBladeProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":light_blade"));
    public static final RegistryObject<EntityType<GiantLightBladeProjectile>> GIANT_LIGHT_BLADE = Beapi.registerEntityType("Giant Light Blade",
            () -> BeModApi.createEntityType(GiantLightBladeProjectile::new)
                    .sized(1.5f, 1.5f)
                    .build(Main.MODID + ":giant_light_blade"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(LIGHT_BLADE.get(), new AbilityProjectileRenderer.Factory(new LightBladeModel())
                .setTexture("light", "light_blade").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(GIANT_LIGHT_BLADE.get(), new AbilityProjectileRenderer.Factory(new LightBladeModel())
                .setTexture("light", "light_blade").setScale(9));

    }
}
