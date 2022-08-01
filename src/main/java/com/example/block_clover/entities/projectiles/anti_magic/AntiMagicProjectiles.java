package com.example.block_clover.entities.projectiles.anti_magic;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.entities.projectiles.darkness.AvidyaSlashProjectile;
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
public class AntiMagicProjectiles {
    public static final RegistryObject<EntityType<BlackSlashProjectile>> BLACK_SLASH = Beapi.registerEntityType("Black Slash",
            () -> Beapi.createEntityType(BlackSlashProjectile::new)
                    .sized(2f, 1f)
                    .build(Main.MODID + ":black_slash"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(BLACK_SLASH.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("darkness", "avidyaslash"));
    }
}
