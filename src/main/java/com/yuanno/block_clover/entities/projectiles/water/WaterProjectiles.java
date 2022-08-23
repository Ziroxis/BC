package com.yuanno.block_clover.entities.projectiles.water;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import com.yuanno.block_clover.models.CubeModel;
import com.yuanno.block_clover.models.projectiles.slash.DeathScytheModel;
import com.yuanno.block_clover.models.projectiles.water.PointBlankDragonModel;
import com.yuanno.block_clover.models.projectiles.water.WaterDragonModel;
import com.yuanno.block_clover.models.projectiles.water.WaterSpearModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WaterProjectiles {

    public static final RegistryObject<EntityType<WaterBallProjectile>> WATER_BALL = Beapi.registerEntityType("Water Ball",
            () -> Beapi.createEntityType(WaterBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":water_ball"));
    public static final RegistryObject<EntityType<WaterDragonProjectile>> WATER_DRAGON = Beapi.registerEntityType("Water Dragon",
            () -> Beapi.createEntityType(WaterDragonProjectile::new)
                    .sized(3f, 3f)
                    .build(Main.MODID + ":water_dragon"));
    public static final RegistryObject<EntityType<PointBlankDragonProjectile>> POINT_BLANK_DRAGON = Beapi.registerEntityType("Point Blank Dragon",
            () -> Beapi.createEntityType(PointBlankDragonProjectile::new)
                    .sized(3f, 3f)
                    .build(Main.MODID + ":point_blank_dragon"));
    public static final RegistryObject<EntityType<WaterSpearProjectile>> WATER_SPEAR = Beapi.registerEntityType("Water Spear",
            () -> Beapi.createEntityType(WaterSpearProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":water_spear"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WATER_BALL.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setTexture("water", "waterball_texture").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(WATER_DRAGON.get(), new AbilityProjectileRenderer.Factory(new WaterDragonModel())
                .setTexture("water", "waterdragon_texture").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(POINT_BLANK_DRAGON.get(), new AbilityProjectileRenderer.Factory(new PointBlankDragonModel())
                .setTexture("water", "pointblankdragon_texture").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(WATER_SPEAR.get(), new AbilityProjectileRenderer.Factory(new WaterSpearModel())
                .setTexture("water", "waterspear_texture").setScale(1));

    }
}
