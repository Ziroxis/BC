package com.example.block_clover.entities.projectiles.slash;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.AbilityProjectileRenderer;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.example.block_clover.models.projectiles.slash.DeathScytheModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SlashProjectiles {
    public static final RegistryObject<EntityType<LightBladeProjectile>> DEATH_SCYTHE = Beapi.registerEntityType("Death Scythe",
            () -> Beapi.createEntityType(DeathScytheProjectile::new)
                    .sized(2f, 0.5f)
                    .build(Main.MODID + ":death_scythe"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(DEATH_SCYTHE.get(), new AbilityProjectileRenderer.Factory(new DeathScytheModel())
                .setTexture("wind", "windblade").setScale(1));
    }
}
