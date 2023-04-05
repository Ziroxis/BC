package com.yuanno.block_clover.entities.projectiles.darkness;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.wind.WindCrescentModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DarknessProjectiles {

    public static final RegistryObject<EntityType<AvidyaSlashProjectile>> AVIDYA_SLASH = Beapi.registerEntityType("Avidya Slash",
            () -> Beapi.createEntityType(AvidyaSlashProjectile::new)
                    .sized(2f, 1f)
                    .build(Main.MODID + ":avidya_slash"));
    public static final RegistryObject<EntityType<AvidyaSlashEvolvedProjectile>> AVIDYA_SLASH_EVOLVED = Beapi.registerEntityType("Avidya Slash evolved",
            () -> Beapi.createEntityType(AvidyaSlashEvolvedProjectile::new)
                    .sized(6f, 1f)
                    .build(Main.MODID + ":avidya_slash"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(AVIDYA_SLASH.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("darkness", "avidyaslash"));
        RenderingRegistry.registerEntityRenderingHandler(AVIDYA_SLASH_EVOLVED.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("darkness", "avidyaslash").setScale(9));
    }
}
