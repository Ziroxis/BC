package com.yuanno.block_clover.client;

import com.yuanno.block_clover.client.overlay.renderer.*;
import com.yuanno.block_clover.client.renderers.entities.beastial.CloverSharkRenderer;
import com.yuanno.block_clover.client.renderers.entities.beastial.FlameBoarRenderer;
import com.yuanno.block_clover.client.renderers.entities.beastial.MonkeyEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.devils.LilithDevilEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.devils.NahamanDevilEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.devils.WalgnerDevilEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.humanoid.BanditEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.humanoid.GrimoireMagicianRenderer;
import com.yuanno.block_clover.client.renderers.entities.humanoid.GuildEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.humanoid.QuestBoardManagerEntityRenderer;
import com.yuanno.block_clover.client.renderers.entities.misc.VolcanoMonsterRenderer;
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
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GUILD_ENTITY.get(), new GuildEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FLAME_BOAR.get(), new FlameBoarRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.WALGNER_DEVIL.get(), new WalgnerDevilEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LILITH_DEVIL.get(), new LilithDevilEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NAHAMAN_DEVIL.get(), new NahamanDevilEntityRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.QUEST_MANAGER.get(), new QuestBoardManagerEntityRenderer.Factory());

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
            layers.add(new ManaSkinRenderer<>(renderer));
            layers.add(new BearClawRenderer<>(renderer));
            layers.add(new RhinocerosArmorRenderer<>(renderer));
            layers.add(new WagnerDevilFamiliarRenderer<>(renderer));
            layers.add(new LilithDevilFamiliarRenderer<>(renderer));
            layers.add(new NahamanDevilFamiliarRenderer<>(renderer));
            layers.add(new ManaLayerRenderer<>(renderer));
        }
    }
}
