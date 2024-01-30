package com.yuanno.block_clover.entities.projectiles.gravity;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.CubeModel;
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
public class GravityProjectiles {

    public static final RegistryObject<EntityType<SingularityProjectile>> SINGULARITY = Beapi.registerEntityType("Singularity",
            () -> BeModApi.createEntityType(SingularityProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":singularity"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(SINGULARITY.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setTexture("gravity", "singularity").setScale(1));
    }
}
