package com.yuanno.block_clover.entities.projectiles.lightning;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileRenderer;
import com.yuanno.block_clover.models.projectiles.lightning.ThunderOrbModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LightningProjectiles {

    public static final RegistryObject<EntityType<LightningOrbProjectile>> LIGHTNING_ORB = Beapi.registerEntityType("Lightning Orb",
            () -> Beapi.createEntityType(LightningOrbProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":lightning_orb"));
    public static final RegistryObject<EntityType<GiantLightningOrbProjectile>> GIANT_LIGHTNING_ORB = Beapi.registerEntityType("Giant Lightning Orb",
            () -> Beapi.createEntityType(GiantLightningOrbProjectile::new)
                    .sized(3f, 3f)
                    .build(Main.MODID + ":giant_lightning_orb"));



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(GIANT_LIGHTNING_ORB.get(), new AbilityProjectileRenderer.Factory(new ThunderOrbModel())
                .setTexture("lightning", "lightning_orb").setScale(9));

        RenderingRegistry.registerEntityRenderingHandler(LIGHTNING_ORB.get(), new AbilityProjectileRenderer.Factory(new ThunderOrbModel())
                .setTexture("lightning", "lightning_orb").setScale(1));
    }
}
