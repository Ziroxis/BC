package com.yuanno.block_clover;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.yuanno.block_clover.api.ability.AbilityArgument;
import com.yuanno.block_clover.api.ability.AbilityGroupArgument;
import com.yuanno.block_clover.api.curios.CuriosApi;
import com.yuanno.block_clover.api.curios.SlotTypeMessage;
import com.yuanno.block_clover.api.curios.SlotTypePreset;
import com.yuanno.block_clover.client.ClientHandler;
import com.yuanno.block_clover.client.curios.*;
import com.yuanno.block_clover.client.curios.gui.CuriosScreen;
import com.yuanno.block_clover.client.curios.gui.GuiEventHandler;
import com.yuanno.block_clover.client.gui.ManaBarOverlay;
import com.yuanno.block_clover.curios.CuriosConfig;
import com.yuanno.block_clover.curios.CuriosHelper;
import com.yuanno.block_clover.curios.CuriosRegistry;
import com.yuanno.block_clover.curios.capability.CurioInventoryCapability;
import com.yuanno.block_clover.curios.capability.CurioItemCapability;
import com.yuanno.block_clover.curios.event.CuriosEventHandler;
import com.yuanno.block_clover.curios.network.NetworkHandler;
import com.yuanno.block_clover.curios.server.SlotHelper;
import com.yuanno.block_clover.curios.server.command.CurioArgumentType;
import com.yuanno.block_clover.curios.server.command.CuriosSelectorOptions;
import com.yuanno.block_clover.curios.slottype.SlotTypeManager;
import com.yuanno.block_clover.curios.triggers.EquipCurioTrigger;
import com.yuanno.block_clover.world.structure.configured.ConfiguredStructures;
import com.yuanno.block_clover.init.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mod(Main.MODID)
public class Main
{
    // Directly reference a log4j logger.
    public static final String MODID = "block_clover";
    public static final Logger LOGGER = LogManager.getLogger();
    private static final boolean DEBUG = false;


    public Main() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CuriosClientMod::init);

        ModRegistry.ENTITY_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new AttachingCapabilities.Registry());
        ModAbilities.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CuriosClientConfig.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CuriosConfig.SERVER_SPEC);

        modEventBus.addListener(this::process);
        MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
        MinecraftForge.EVENT_BUS.addListener(this::serverStopped);
        modEventBus.addListener(this::config);
        modEventBus.addListener(this::enqueue);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void enqueue(InterModEnqueueEvent evt)
    {
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
        InterModComms.sendTo(Main.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BRACELET.getMessageBuilder().build());

        if (DEBUG) {
            InterModComms.sendTo(MODID, SlotTypeMessage.REGISTER_TYPE,
                    () -> Arrays.stream(SlotTypePreset.values())
                            .map(preset -> preset.getMessageBuilder().build()).collect(Collectors.toList()));
        }
    }
    private void config(final ModConfig.Loading evt) {

        if (evt.getConfig().getModId().equals(MODID)) {

            if (evt.getConfig().getType() == ModConfig.Type.SERVER) {
                ForgeConfigSpec spec = evt.getConfig().getSpec();
                CommentedConfig commentedConfig = evt.getConfig().getConfigData();

                if (spec == CuriosConfig.SERVER_SPEC) {
                    CuriosConfig.transformCurios(commentedConfig);
                    SlotTypeManager.buildConfigSlotTypes();
                }
            }
        }
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        CuriosApi.setCuriosHelper(new CuriosHelper());
        CurioInventoryCapability.register();
        CurioItemCapability.register();
        CuriosSelectorOptions.register();
        MinecraftForge.EVENT_BUS.register(new CuriosEventHandler());
        CriteriaTriggers.register(EquipCurioTrigger.INSTANCE);
        ArgumentTypes.register("curios:slot_type", CurioArgumentType.class,
                new ArgumentSerializer<>(CurioArgumentType::slot));


        ModCapabilities.init();
        ModNetwork.init();
        NetworkHandler.register();

        event.enqueueWork(() ->
        {
           ModStructures.setupStructures();
            ConfiguredStructures.registerConfiguredStructures();
        });

        ArgumentTypes.register("ability", AbilityArgument.class, new ArgumentSerializer<>(AbilityArgument::ability));
        ArgumentTypes.register("group", AbilityGroupArgument.class, new ArgumentSerializer<>(AbilityGroupArgument::abilityGroup));

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        CuriosApi.setIconHelper(new IconHelper());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        ScreenManager.register(CuriosRegistry.CONTAINER_TYPE, CuriosScreen::new);
        KeyRegistry.registerKeys();
        ClientHandler.onSetup();
        ModKeyBinds.init();
        MinecraftForge.EVENT_BUS.register(new ManaBarOverlay());
    }
    private void process(InterModProcessEvent evt) {
        SlotTypeManager.buildImcSlotTypes(evt.getIMCStream(SlotTypeMessage.REGISTER_TYPE::equals),
                evt.getIMCStream(SlotTypeMessage.MODIFY_TYPE::equals));
    }
    private void serverAboutToStart(FMLServerAboutToStartEvent evt) {
        CuriosApi.setSlotHelper(new SlotHelper());
        SlotTypeManager.buildSlotTypes();
    }
    private void serverStopped(FMLServerStoppedEvent evt) {
        CuriosApi.setSlotHelper(null);
    }

    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientProxy
    {
        @SubscribeEvent
        public static void stitchTextures(TextureStitchEvent.Pre evt)
        {
            CuriosClientMod.stitch(evt);
            evt.addSprite(new ResourceLocation(Main.MODID, "item/crown"));

        }
    }


}