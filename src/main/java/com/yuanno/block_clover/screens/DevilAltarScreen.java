package com.yuanno.block_clover.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.containers.JuicerContainer;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import com.yuanno.block_clover.blocks.tileentities.JuicerBlockTileEntity;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DevilAltarScreen extends ContainerScreen<DevilAltarContainer> {
    private DevilAltarTileEntity tileEntity;
    private DevilAltarContainer container;
    // Location for texture
    private final ResourceLocation GUI_LOC = new ResourceLocation(Main.MODID, "textures/gui/devil_altar_gui.png");

    public DevilAltarScreen(DevilAltarContainer container, PlayerInventory inv, ITextComponent text) {
        super(container, inv, text);
        if (container.tileEntity instanceof JuicerBlockTileEntity) {
            this.tileEntity = (DevilAltarTileEntity) container.tileEntity;
            this.container = container;
        }
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