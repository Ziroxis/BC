package com.example.block_clover;

import com.example.block_clover.api.ability.AbilityArgument;
import com.example.block_clover.api.ability.AbilityGroupArgument;
import com.example.block_clover.client.ClientHandler;
import com.example.block_clover.client.gui.ManaBarOverlay;
import com.example.block_clover.init.*;
import com.example.block_clover.world.structure.configured.ConfiguredStructures;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main
{
    // Directly reference a log4j logger.
    public static final String MODID = "block_clover";
    private static final Logger LOGGER = LogManager.getLogger();


    public Main() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModRegistry.ENTITY_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new AttachingCapabilities.Registry());
        ModAbilities.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModCapabilities.init();
        ModNetwork.init();

        event.enqueueWork(() ->
        {
           ModStructures.setupStructures();
            ConfiguredStructures.registerConfiguredStructures();
        });

        ArgumentTypes.register("ability", AbilityArgument.class, new ArgumentSerializer<>(AbilityArgument::ability));
        ArgumentTypes.register("group", AbilityGroupArgument.class, new ArgumentSerializer<>(AbilityGroupArgument::abilityGroup));

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientHandler.onSetup();
        ModKeyBinds.init();
        MinecraftForge.EVENT_BUS.register(new ManaBarOverlay());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }


}
