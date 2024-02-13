package com.yuanno.block_clover.entities.projectiles.fire;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.yuanno.block_clover.models.projectiles.fire.FireBallModel;
import com.yuanno.block_clover.models.projectiles.fire.FlameRoarModel;
import com.yuanno.block_clover.models.projectiles.fire.SolLineaModel;
import com.yuanno.block_clover.models.projectiles.fire.SpiralFlameModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FireProjectiles {
    public static final RegistryObject<EntityType<FireBallProjectile>> FIRE_BALL = Beapi.registerEntityType("Fire Ball",
            () -> BeModApi.createEntityType(FireBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":fire_ball"));
    public static final RegistryObject<EntityType<FireBallDarkProjectile>> FIRE_BALL_DARK = Beapi.registerEntityType("Fire Ball Dark",
            () -> BeModApi.createEntityType(FireBallDarkProjectile::new)
                    .sized(3f, 3f)
                    .build(Main.MODID + ":fire_ball_dark"));
    public static final RegistryObject<EntityType<GiantFireBallProjectile>> GIANT_FIRE_BALL = Beapi.registerEntityType("Giant Fire Ball",
            () -> BeModApi.createEntityType(GiantFireBallProjectile::new)
                    .sized(3f, 3f)
                    .build(Main.MODID + ":giant_fire_ball"));
    public static final RegistryObject<EntityType<FlameRoarProjectile>> FLAME_ROAR = Beapi.registerEntityType("Flame Roar",
            () -> BeModApi.createEntityType(FlameRoarProjectile::new)
                    .sized(4f, 4f)
                    .build(Main.MODID + ":flame_roar"));
    public static final RegistryObject<EntityType<SolLineaProjectile>> SOL_LINEA = Beapi.registerEntityType("Sol Linea",
            () -> BeModApi.createEntityType(LightBladeProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":sol_linea"));
    public static final RegistryObject<EntityType<SpiralFlameProjectile>> SPIRAL_FLAME = Beapi.registerEntityType("Spiral Flame",
            () -> BeModApi.createEntityType(SpiralFlameProjectile::new)
                    .sized(2f, 2f)
                    .build(Main.MODID + ":spiral_flame"));



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(FIRE_BALL_DARK.get(), new AbilityProjectileRenderer.Factory(new FireBallModel())
                .setTexture("fire", "fireball_dark").setScale(9));
        RenderingRegistry.registerEntityRenderingHandler(FIRE_BALL.get(), new AbilityProjectileRenderer.Factory(new FireBallModel())
                .setTexture("fire", "fireball").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(GIANT_FIRE_BALL.get(), new AbilityProjectileRenderer.Factory(new FireBallModel())
                .setTexture("fire", "fireball").setScale(9));
        RenderingRegistry.registerEntityRenderingHandler(FLAME_ROAR.get(), new AbilityProjectileRenderer.Factory(new FlameRoarModel())
                .setTexture("fire", "flameroar").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(SOL_LINEA.get(), new AbilityProjectileRenderer.Factory(new SolLineaModel())
                .setTexture("fire", "sollinea").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(SPIRAL_FLAME.get(), new AbilityProjectileRenderer.Factory(new SpiralFlameModel())
                .setTexture("fire", "spiralflame").setScale(1));

    }
}
