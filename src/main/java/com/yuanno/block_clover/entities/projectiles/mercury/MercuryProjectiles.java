package com.yuanno.block_clover.entities.projectiles.mercury;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.mercury.MercuryBulletModel;
import com.yuanno.block_clover.models.projectiles.mercury.MercuryRainModel;
import com.yuanno.block_clover.models.projectiles.mercury.MercurySpearModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MercuryProjectiles {

    public static final RegistryObject<EntityType<MercuryBulletProjectile>> MERCURY_BULLET = Beapi.registerEntityType("Mercury Bullet",
            () -> Beapi.createEntityType(MercuryBulletProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":mercury_bullet"));
    public static final RegistryObject<EntityType<MercurySpearProjectile>> MERCURY_SPEAR = Beapi.registerEntityType("Mercury Spear",
            () -> Beapi.createEntityType(MercurySpearProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":mercury_spear"));
    public static final RegistryObject<EntityType<MercuryRainProjectile>> MERCURY_RAIN = Beapi.registerEntityType("Mercury Rain",
            () -> Beapi.createEntityType(MercuryRainProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":mercury_rain"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(MERCURY_BULLET.get(), new AbilityProjectileRenderer.Factory(new MercuryBulletModel())
                .setTexture("mercury", "mercury_bullet").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(MERCURY_SPEAR.get(), new AbilityProjectileRenderer.Factory(new MercurySpearModel())
                .setTexture("mercury", "mercury_spear").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(MERCURY_RAIN.get(), new AbilityProjectileRenderer.Factory(new MercuryRainModel())
                .setTexture("mercury", "mercury_rain").setScale(1));
    }
}
