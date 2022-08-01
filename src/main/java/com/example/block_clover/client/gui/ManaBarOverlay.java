package com.example.block_clover.client.gui;

import com.example.block_clover.Main;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ManaBarOverlay {
    private final ResourceLocation manaBar = new ResourceLocation(Main.MODID + ":textures/gui/mana_bars.png");
    private final int tex_width = 9, tex_height = 102, bar_width = 7, bar_height = 100;
    int mana = 0;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String attribute = entityStats.getAttribute();
        switch (attribute)
        {
            case "Wind":
            case "Slash":
                mana = 1;
                break;
            case "Fire":
                mana = 6;
                break;
            case "Light":
                mana = 7;
                break;
            case "Lightning":
                mana = 3;
                break;
            case "Darkness":
                mana = 2;
                break;
            case "Earth":
                mana = 4;
                break;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            //ClientPlayerEntity player = Minecraft.getInstance().player;
            //IEntityStats entityStats = EntityStatsCapability.get(player);

            if (entityStats.getMaxMana() > 0) {
                Minecraft mc = Minecraft.getInstance();



                int colour_x = ((mana * 8) + 9); // CORRECT FORMULA ((chakra.returncolorChakra() * 8) + 9)
                mc.textureManager.bind(manaBar);
                mc.gui.blit(event.getMatrixStack(), 20, 130, 0, 0, tex_width, tex_height);
                if (entityStats.getMaxMana() <= 0) {
                    int set_height = tex_height;
                    mc.gui.blit(event.getMatrixStack(), 20, 130, colour_x, 0, tex_width, set_height); // set_height
                } else {
                    float manaRatio = (entityStats.getMana() / entityStats.getMaxMana());
                    int set_height = (int) (tex_height * manaRatio);//(int) (bar_height * chakraratio)
                    int move_tex = (tex_height - set_height);
                    mc.gui.blit(event.getMatrixStack(), 20, 130 + move_tex, colour_x, move_tex, tex_width, set_height); // set_height
                }
            }
        }
    }
}
