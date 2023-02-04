package com.yuanno.block_clover.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.blocks.containers.JuicerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class JuicerScreen extends ContainerScreen<JuicerContainer> {
    // Location for texture
    private final ResourceLocation GUI_LOC = new ResourceLocation(Main.MODID, "textures/gui/juicer_gui.png");

    public JuicerScreen(JuicerContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    public void render(MatrixStack matrix, int x, int y, float ticks) {
        this.renderBackground(matrix);
        super.render(matrix, x, y, ticks);
        this.renderTooltip(matrix, x, y);
    }

    // Render background and GUI
    @Override
    protected void renderBg(MatrixStack matrix, float ticks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI_LOC);
        int left = this.getGuiLeft();
        int top = this.getGuiTop();

        this.blit(matrix, left, top, 0, 0, this.getXSize(), this.getYSize());
    }
}
