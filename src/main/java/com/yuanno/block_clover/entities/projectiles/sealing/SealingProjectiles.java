package com.yuanno.block_clover.entities.projectiles.sealing;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.CubeModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SealingProjectiles {

    public static final RegistryObject<EntityType<SealingProjectile>> SEALING_PROJECTILE = Beapi.registerEntityType("Sealing Projectile",
            () -> Beapi.createEntityType(SealingProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":sealing_projectile"));
    public static final RegistryObject<EntityType<EvolvedSealingProjectile>> EVOLVED_SEALING_PROJECTILE = Beapi.registerEntityType("Evolved Sealing Projectile",
            () -> Beapi.createEntityType(EvolvedSealingProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":evolved_sealing_projectile"));



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(SEALING_PROJECTILE.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setTexture("sealing", "sealing_texture").setScale(1));
        RenderingRegistry.registerEntityRenderingHandler(EVOLVED_SEALING_PROJECTILE.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setTexture("sealing", "sealing_texture").setScale(1));
    }
}
