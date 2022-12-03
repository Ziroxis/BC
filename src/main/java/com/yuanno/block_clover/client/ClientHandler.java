package com.yuanno.block_clover.client;

import com.yuanno.block_clover.client.overlay.renderer.*;
import com.yuanno.block_clover.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void onSetup()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLOVER_SHARK.get(), new CloverSharkRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MONKEY_ENTITY.get(), new MonkeyEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.VOLCANO_MONSTER.get(), new VolcanoMonsterRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT.get(), new BanditEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GRIMOIRE_MAGICIAN.get(), new GrimoireMagicianRenderer.Factory());
        Map<String, PlayerRenderer> playerSkinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        ClientHandler.addPlayerLayers(playerSkinMap.get("default"));
        ClientHandler.addPlayerLayers(playerSkinMap.get("slim"));

    }

    @OnlyIn(Dist.CLIENT)
    public static void addPlayerLayers(PlayerRenderer renderer)
    {
        List<LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> layers = ObfuscationReflectionHelper.getPrivateValue(LivingRenderer.class, renderer, "field_177097_h");
        if(layers != null)
        {
            layers.add(new GrimoirRenderer<>(renderer));
            layers.add(new BlackModeRenderer<>(renderer));
            layers.add(new EarthManipulationRenderer<>(renderer));
            layers.add(new LeoPalmaRenderer<>(renderer));
            layers.add(new SlashBladesRenderer<>(renderer));
            layers.add(new ThunderGodGearRenderer<>(renderer));
            layers.add(new EarthGlovesRenderer<>(renderer));
            layers.add(new ValkyrieArmorRenderer<>(renderer));
        }
    }
}
