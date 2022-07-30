package com.example.block_clover.init;

import com.example.block_clover.Main;
import com.example.block_clover.particles.GenericParticleData;
import com.example.block_clover.particles.SimpleParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Main.MODID);

    public static final RegistryObject<ParticleType<GenericParticleData>> FIRE = PARTICLE_TYPES.register("fire", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> DARKNESS = PARTICLE_TYPES.register("darkness", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> LIGHT = PARTICLE_TYPES.register("light", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> LIGHTNING = PARTICLE_TYPES.register("lightning", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> WATER = PARTICLE_TYPES.register("water", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> WIND = PARTICLE_TYPES.register("wind", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> SLASH = PARTICLE_TYPES.register("slash", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> EARTH = PARTICLE_TYPES.register("earth", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> ANTI_MAGIC = PARTICLE_TYPES.register("anti_magic", GenericParticleData::new);


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
    {
        final ParticleManager manager = Minecraft.getInstance().particleEngine;

        manager.register(ModParticleTypes.FIRE.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/fire_particle.png")));
        manager.register(ModParticleTypes.DARKNESS.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/darkness_particle.png")));
        manager.register(ModParticleTypes.LIGHT.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/light_particle.png")));
        manager.register(ModParticleTypes.LIGHTNING.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/lightning_particle.png")));
        manager.register(ModParticleTypes.WATER.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/water_particle.png")));
        manager.register(ModParticleTypes.WIND.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/water_particle.png")));
        manager.register(ModParticleTypes.SLASH.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/slash_particle.png")));
        manager.register(ModParticleTypes.EARTH.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/earth_particle.png")));
        manager.register(ModParticleTypes.ANTI_MAGIC.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/anti_magic_particle.png")));

    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
