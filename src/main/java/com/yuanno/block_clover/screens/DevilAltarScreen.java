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
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class DevilAltarScreen extends ContainerScreen<DevilAltarContainer> {
    private DevilAltarTileEntity tileEntity;
    private DevilAltarContainer container;
    // Location for texture
    private final ResourceLocation GUI_LOC = new ResourceLocation(Main.MODID, "textures/gui/devil_altar_gui.png");
    private final ResourceLocation BUTTON_TEX = new ResourceLocation(Main.MODID, "textures/gui/devil_altar_button.png");

    public DevilAltarScreen(DevilAltarContainer container, PlayerInventory inv, ITextComponent text) {
        super(container, inv, text);
        if (container.tileEntity instanceof JuicerBlockTileEntity) {
            this.tileEntity = (DevilAltarTileEntity) container.tileEntity;
            this.container = container;
        }
    }

    public void init() {
        super.init();
        this.addButton(new ImageButton(this.leftPos+50,this.height/2+10,10,10,0,0,10,BUTTON_TEX, (p_214087_1_) -> {
        }));
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
        int left = this.getGuiLeft() - 60;
        int top = this.getGuiTop() + 0;

        this.blit(matrix, left, top, 0, 0, 256, 256);
    }
}