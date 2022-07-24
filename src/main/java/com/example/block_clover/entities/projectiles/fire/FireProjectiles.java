package com.example.block_clover.entities.projectiles.fire;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.example.block_clover.models.projectiles.fire.FireBallModel;
import com.example.block_clover.models.projectiles.fire.FlameRoarModel;
import com.example.block_clover.models.projectiles.fire.SolLineaModel;
import com.example.block_clover.models.projectiles.fire.SpiralFlameModel;
import com.example.block_clover.models.projectiles.light.LightBladeModel;
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
            () -> Beapi.createEntityType(FireBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":fire_ball"));
    public static final RegistryObject<EntityType<FlameRoarProjectile>> FLAME_ROAR = Beapi.registerEntityType("Flame Roar",
            () -> Beapi.createEntityType(FlameRoarProjectile::new)
                    .sized(4f, 4f)
                    .build(Main.MODID + ":flame_roar"));
    public static final RegistryObject<EntityType<SolLineaProjectile>> SOL_LINEA = Beapi.registerEntityType("Sol Linea",
            () -> Beapi.createEntityType(LightBladeProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":sol_linea"));
    public static final RegistryObject<EntityType<SpiralFlameProjectile>> SPIRAL_FLAME = Beapi.registerEntityType("Spiral Flame",
            () -> Beapi.createEntityType(SpiralFlameProjectile::new)
                    .sized(2f, 2f)
                    .build(Main.MODID + ":spiral_flame"));



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(FIRE_BALL.get(), new AbilityProjectileRenderer.Factory(new FireBallModel())
                .setTexture("fire", "fireball").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(FLAME_ROAR.get(), new AbilityProjectileRenderer.Factory(new FlameRoarModel())
                .setTexture("fire", "flameroar").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(SOL_LINEA.get(), new AbilityProjectileRenderer.Factory(new SolLineaModel())
                .setTexture("fire", "sollinea").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(SPIRAL_FLAME.get(), new AbilityProjectileRenderer.Factory(new SpiralFlameModel())
                .setTexture("fire", "spiralflame").setScale(1));

    }
}
