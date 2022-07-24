package com.example.block_clover.entities.projectiles.lightning;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.example.block_clover.models.projectiles.light.LightBladeModel;
import com.example.block_clover.models.projectiles.lightning.ThunderOrbModel;
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


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(LIGHTNING_ORB.get(), new AbilityProjectileRenderer.Factory(new ThunderOrbModel())
                .setTexture("lightning", "lightning_orb").setScale(1));
    }
}
