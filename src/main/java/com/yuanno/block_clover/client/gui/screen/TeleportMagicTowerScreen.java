package com.yuanno.block_clover.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.client.gui.button.NoTextureButton;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncQuestDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.client.CTeleportMagicTowerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class TeleportMagicTowerScreen extends Screen {

    float xMouse;
    float yMouse;
    private int guiLeft;
    private int guiTop;
    private final PlayerEntity player;
    private final int xSize = 64;
    private final int ySize = 58;

    public TeleportMagicTowerScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;


        this.addButton(new Button(guiLeft - 20, guiTop + 10, 100, 20, new TranslationTextComponent("Teleport"), button -> {
            PacketHandler.sendToServer(new CTeleportMagicTowerPacket());
            mc.setScreen(null);
        }, (but, matrixStack, mouseX, mouseY) -> {
            if (but.isHovered()) {
                renderTooltip(matrixStack, new StringTextComponent("Teleporting will immediately teleport you there leaving everything behind!"), mouseX, mouseY);
            }
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.renderBackground(matrixStack);
        drawString(matrixStack, font, TextFormatting.WHITE+ "You just reached level 5, allowing you to get your grimoire.", posX -17, posY + 80, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.WHITE+ "Do you want to travel to the grimoire tower?", posX + 0, posY + 89, Color.GRAY.getRGB());



        super.render(matrixStack, x, y, f);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
    public static void open()
    {
        Minecraft.getInstance().setScreen(new TeleportMagicTowerScreen());
    }
}
