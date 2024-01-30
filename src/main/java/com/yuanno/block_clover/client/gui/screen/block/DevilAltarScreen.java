package com.yuanno.block_clover.client.gui.screen.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import com.yuanno.block_clover.blocks.tileentities.JuicerBlockTileEntity;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.init.ModChallenges;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CStartChallengePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class DevilAltarScreen extends ContainerScreen<DevilAltarContainer> {
    private DevilAltarTileEntity tileEntity;
    private DevilAltarContainer container;
    // Location for texture
    private final ResourceLocation GUI_LOC = new ResourceLocation(Main.MODID, "textures/gui/devil_altar_gui.png");
    private final ResourceLocation BUTTON_TEX = new ResourceLocation(Main.MODID, "textures/gui/devil_altar_button.png");
    private final LivingEntity[] group = new LivingEntity[1];
    private final IChallengesData challengesDataBase;
    private final PlayerEntity player;


    public DevilAltarScreen(DevilAltarContainer container, PlayerInventory inv, ITextComponent text) {
        super(container, inv, text);
        this.player = Minecraft.getInstance().player;
        if (container.tileEntity instanceof JuicerBlockTileEntity) {
            this.tileEntity = (DevilAltarTileEntity) container.tileEntity;
            this.container = container;
        }
        this.challengesDataBase = ChallengesDataCapability.get(player);

    }

    public void init() {
        super.init();
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;

        addChallengeButton(posX - 65, posY + 20, ModChallenges.WALGNER_DEVIL.get(), "Walgner", "Walgner");

    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText) {
        addChallengeButton(x, y, challenge, buttonText, tooltipText, false);
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText, boolean checkCompletion) {
        net.minecraft.client.gui.widget.button.Button button = new Button(x, y, 60, 20, new TranslationTextComponent(buttonText), b -> {
            PacketHandler.sendToServer(new CStartChallengePacket(challenge.getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (but, matrixStack, mouseX, mouseY) -> {
            if (but.isHovered()) {
                this.renderTooltip(matrixStack, new TranslationTextComponent(tooltipText), mouseX, mouseY);
            }
        });

        if (checkCompletion) {
            button.active = challengesDataBase.hasChallenge(challenge) || challengesDataBase.isChallengeCompleted(challenge);
        } else {
            button.active = challengesDataBase.hasChallenge(challenge);
        }

        this.addButton(button);
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