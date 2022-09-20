package com.yuanno.block_clover.client.gui;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class TimeBarOverlay {

    private final ResourceLocation timeBar = new ResourceLocation(Main.MODID + ":textures/gui/mana_bars.png");
    private final int tex_width = 9, tex_height = 102, bar_width = 7, bar_height = 100;
    int time = 0;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            if (entityStats.getTime() > 0 && entityStats.isInCombatMode())
            {
                Minecraft mc = Minecraft.getInstance();
                String time = "" + entityStats.getTime();
                int colour_x = 0;
                mc.textureManager.bind(timeBar);
                mc.gui.blit(event.getMatrixStack(), 63, 130, 0, 0, tex_width, tex_height);
                float timeRatio = (float) (entityStats.getTime() / 100.0f);
                System.out.println(entityStats.getTime());
                int set_height = (int) (tex_height * timeRatio);
                int move_tex = (tex_height - set_height);
                mc.gui.blit(event.getMatrixStack(), 63, 130 + move_tex, colour_x, move_tex, tex_width, set_height); // set_height
                Beapi.drawStringWithBorder(Minecraft.getInstance().font, event.getMatrixStack(), TextFormatting.BOLD + time, 80, 220, Beapi.hexToRGB("#00FF00").getRGB());
            }
        }
    }
}
