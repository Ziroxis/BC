package com.yuanno.block_clover.client.gui.screen.overview;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class PlayerStatsScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;

    public PlayerStatsScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;




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
        statsRendering(matrixStack);

       // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);

        super.render(matrixStack, x, y, f);
    }

    public void statsRendering(MatrixStack matrixStack)
    {
        PlayerEntity player = this.getMinecraft().player;
        IEntityStats props = EntityStatsCapability.get(player);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        int level = props.getLevel();
        float maxHealth = player.getMaxHealth();
        float maxMana = props.getMaxMana();
        float manaRegeneration = props.getManaRegeneration();
        String title = props.getTitle();
        String rank = props.getRank();

/*
        String name = player.getName().getString();
        String race = props.getRace();
        String attribute = props.getAttribute();
        String secondAttribute = props.getSecondAttribute();
        int level = props.getLevel();
        int experience = props.getExperience();
        int maxExperience = props.getMaxExperience();



        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, posX - 30, posY + 50, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Magic level: " + TextFormatting.RESET + level, posX - 30, posY + 70, -1);
        if (props.hasSecondAttribute())
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Attribute: " + TextFormatting.RESET + attribute + ", " + secondAttribute, posX - 30, posY + 90, -1);
        else
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Attribute: " + TextFormatting.RESET + attribute, posX - 30, posY + 90, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, posX - 30, posY + 110, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Experience: " + TextFormatting.RESET + experience + "/" + maxExperience, posX - 30, posY + 130, -1);
 */
        drawString(matrixStack, font, TextFormatting.GRAY + "INFO CARD", posX + 102, posY + 30, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "Level: " + TextFormatting.RESET + level, posX - 30, posY + 50, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Max Health: " + TextFormatting.RESET + maxHealth, posX - 30, posY + 70, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Max Mana: " + TextFormatting.RESET + maxMana, posX - 30, posY + 90, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Mana Regeneration: " + TextFormatting.RESET + manaRegeneration, posX - 30, posY + 110, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Title: " + TextFormatting.RESET + title, posX - 30, posY + 130, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Rank: " + TextFormatting.RESET + rank, posX - 30, posY + 150, -1);




    }


    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new PlayerOverviewScreen());
    }
}
