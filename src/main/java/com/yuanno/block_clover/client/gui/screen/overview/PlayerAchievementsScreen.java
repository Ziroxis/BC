package com.yuanno.block_clover.client.gui.screen.overview;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModValues;
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
public class PlayerAchievementsScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;
    int DRANK = 0;
    int CRANK = 0;
    int BRANK = 0;
    int ARANK = 0;
    int SRANK = 0;
    int SSRANK = 0;
    int SSSRANK = 0;

    private final IQuestData questData;
    public PlayerAchievementsScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.questData = QuestDataCapability.get(player);
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        /*
        for (Quest quest : questData.getFinishedQuests())
        {
            switch (quest.getRank())
            {
                case (ModValues.RANK_QUEST_D):
                    this.DRANK += 1;
                    break;
                case (ModValues.RANK_QUEST_C):
                    this.CRANK += 1;
                    break;
                case (ModValues.RANK_QUEST_B):
                    this.BRANK += 1;
                    break;
                case (ModValues.RANK_QUEST_A):
                    this.ARANK += 1;
                    break;
                case (ModValues.RANK_QUEST_S):
                    this.SRANK += 1;
                    break;
                case (ModValues.RANK_QUEST_SS):
                    this.SSRANK += 1;
                    break;
                case (ModValues.RANK_QUEST_SSS):
                    this.SSSRANK += 1;
                    break;
            }
        }

         */


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


        super.render(matrixStack, x, y, f);
    }

    public void statsRendering(MatrixStack matrixStack)
    {
        PlayerEntity player = this.getMinecraft().player;
        IEntityStats props = EntityStatsCapability.get(player);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        String title = props.getTitle();
        String rank = props.getRank();


        drawString(matrixStack, font, TextFormatting.GRAY + "ACHIEVEMENTS", posX + 102, posY + 30, Color.GRAY.getRGB());


        int leftShift = posX - 30;
        drawString(matrixStack, font, TextFormatting.BOLD + "Title: " + TextFormatting.RESET + title, leftShift, posY + 45, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "Rank: " + TextFormatting.RESET + rank, leftShift, posY + 60, -1);


        int rightShift = posX + 200;
        drawString(matrixStack, font, TextFormatting.BOLD + "SSS", rightShift, posY + 45, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "SS", rightShift, posY + 65, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "S", rightShift, posY + 85, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "A", rightShift, posY + 105, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "B", rightShift, posY + 125, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "C", rightShift, posY + 145, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "D", rightShift, posY + 165, -1);
        int rightShiftSecond = posX + 225;
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 45, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 65, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 85, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 105, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 125, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 145, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "RANK DONE: ", rightShiftSecond, posY + 165, -1);

        int rightShiftShift = posX + 300;
        drawString(matrixStack, font, TextFormatting.BOLD + "" + DRANK, rightShiftShift, posY + 165, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + CRANK, rightShiftShift, posY + 145, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + BRANK, rightShiftShift, posY + 125, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + ARANK, rightShiftShift, posY + 105, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + SRANK, rightShiftShift, posY + 85, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + SSRANK, rightShiftShift, posY + 65, -1);
        drawString(matrixStack, font, TextFormatting.BOLD + "" + SSSRANK, rightShiftShift, posY + 45, -1);

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
