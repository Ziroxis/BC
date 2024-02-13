package com.yuanno.block_clover.client.gui.overlay;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class DevilManaBarOverlay {
    private final ResourceLocation manaBar = new ResourceLocation(Main.MODID + ":textures/gui/mana_bars.png");
    private final int tex_width = 9, tex_height = 102, bar_width = 7, bar_height = 100;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        IDevil devil = DevilCapability.get(player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (devil.getMaxDevilMana() > 0 && entityStats.isInCombatMode()) {
                Minecraft mc = Minecraft.getInstance();
                String manaString = "" + (int) devil.getDevilMana();
                int colour_x = ((5 * 8) + 9);
                mc.textureManager.bind(manaBar);
                mc.gui.blit(event.getMatrixStack(), 20, 130, 0, 0, tex_width, tex_height);

                if (devil.getMaxDevilMana() <= 0)
                {
                    int set_height = tex_height;
                    mc.gui.blit(event.getMatrixStack(), 20, 130, colour_x, 0, tex_width, set_height); // set_height

                }
                else
                {
                    double manaRatio = (devil.getDevilMana() / devil.getMaxDevilMana());
                    int set_height = (int) (tex_height * manaRatio);//(int) (bar_height * chakraratio)
                    int move_tex = (tex_height - set_height);
                    mc.gui.blit(event.getMatrixStack(), 380, 130 + move_tex, colour_x, move_tex, tex_width, set_height); // set_height
                    Beapi.drawStringWithBorder(Minecraft.getInstance().font, event.getMatrixStack(), TextFormatting.ITALIC + manaString,  350, 220, Beapi.hexToRGB("#000000").getRGB());
                }
            }
        }
    }
}
