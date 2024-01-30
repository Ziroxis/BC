package com.yuanno.block_clover.entities.projectiles.sword;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
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
public class SwordProjectiles {
    public static final RegistryObject<EntityType<OriginalSlashProjectile>> ORIGINAL_SLASH = Beapi.registerEntityType("Original slash",
            () -> BeModApi.createEntityType(OriginalSlashProjectile::new)
                    .sized(2f, 1f)
                    .build(Main.MODID + ":original_slash"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(ORIGINAL_SLASH.get(), new AbilityProjectileRenderer.Factory(new WindCrescentModel())
                .setTexture("sword", "original_slash"));
    }
}
