package com.yuanno.block_clover.entities.projectiles.ice;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.earth.EarthChargeModel;
import com.yuanno.block_clover.models.projectiles.earth.EarthChunkModel;
import com.yuanno.block_clover.models.projectiles.ice.IceBallModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IceProjectiles {

    public static final RegistryObject<EntityType<IceBallProjectile>> ICE_BALL = Beapi.registerEntityType("Ice Ball",
            () -> BeModApi.createEntityType(IceBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + "ice_ball"));
    public static final RegistryObject<EntityType<DarkIceBallProjectile>> DARK_ICE_BALL = Beapi.registerEntityType("Dark Ice Ball",
            () -> BeModApi.createEntityType(DarkIceBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + "dark_ice_ball"));
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(ICE_BALL.get(), new AbilityProjectileRenderer.Factory(new IceBallModel())
                .setTexture("ice", "iceball").setScale(0.15));
        RenderingRegistry.registerEntityRenderingHandler(DARK_ICE_BALL.get(), new AbilityProjectileRenderer.Factory(new IceBallModel())
                .setTexture("ice", "iceball_dark").setScale(0.15));
    }
}
