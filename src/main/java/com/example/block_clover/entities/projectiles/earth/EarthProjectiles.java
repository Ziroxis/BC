package com.example.block_clover.entities.projectiles.earth;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.models.projectiles.earth.EarthChargeModel;
import com.example.block_clover.models.projectiles.earth.EarthChunkModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EarthProjectiles {

    public static final RegistryObject<EntityType<EarthChunkProjectile>> EARTH_CHUNK = Beapi.registerEntityType("Earth Chunk",
            () -> Beapi.createEntityType(EarthChunkProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":earth_chunk"));
    public static final RegistryObject<EntityType<EarthChargeProjectile>> EARTH_CHARGE = Beapi.registerEntityType("Earth Charge",
            () -> Beapi.createEntityType(EarthChargeProjectile::new)
                    .sized(2f, 1f)
                    .build(Main.MODID + ":earth_charge"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EARTH_CHUNK.get(), new AbilityProjectileRenderer.Factory(new EarthChunkModel())
                .setTexture("earth", "earthchunk"));
        RenderingRegistry.registerEntityRenderingHandler(EARTH_CHARGE.get(), new AbilityProjectileRenderer.Factory(new EarthChargeModel())
                .setTexture("earth", "earthcharge"));

    }
}
