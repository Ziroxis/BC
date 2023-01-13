package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.guild.Guild;
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

@OnlyIn(Dist.CLIENT)
public class GuildScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;

    public GuildScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;



        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;


        posX += 80;
        this.addButton(new Button(posX, posY + 190, 70, 20, new TranslationTextComponent("gui.blackclover.stats.overview"), b ->
        {
            Minecraft.getInstance().setScreen(new PlayerOverviewScreen());
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.xMouse = (float)x;
        this.yMouse = (float)y;

        this.renderBackground(matrixStack);
        guildRendering(matrixStack);

        // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);

        super.render(matrixStack, x, y, f);

    }

    public void guildRendering(MatrixStack matrixStack)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        drawString(matrixStack, font, TextFormatting.GRAY + "GUILD", posX + 102, posY + 30, Color.GRAY.getRGB());
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new GuildScreen());
    }
}
