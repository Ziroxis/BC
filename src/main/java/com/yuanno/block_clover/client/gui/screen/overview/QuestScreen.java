package com.yuanno.block_clover.client.gui.screen.overview;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
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
public class QuestScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;

    public QuestScreen()
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
        questRendering(matrixStack);

        // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);

        super.render(matrixStack, x, y, f);
    }

    public void questRendering(MatrixStack matrixStack)
    {
        PlayerEntity player = this.getMinecraft().player;
        IEntityStats props = EntityStatsCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);

        Quest[] quests = questData.getInProgressQuests();
        for (int i = 0; i < quests.length; i++)
        {
            if (quests[i] != null)
            {
                Quest questInprogress = questData.getInProgressQuests()[i];
                String questInProgressString = questInprogress.getTitle();
                drawString(matrixStack, font, "Quest: ", guiLeft - 155 , guiTop - 50 + (i * 50), 16777215);
                drawString(matrixStack, font, questInProgressString, guiLeft - 122, guiTop - 50 + (i * 50), 16777215);
                drawString(matrixStack, font, "Objectives: ", guiLeft - 155 , guiTop - 40 + (i * 50), 16777215);

                for (int ia = 0; ia < questInprogress.getObjectives().size(); ia++)
                {
                    drawString(matrixStack, font, "" + questInprogress.getObjectives().get(ia).getTitle(), guiLeft - 155, guiTop - 30 + (i * 50), 16777215);
                    drawString(matrixStack, font, "Progress: " + (int) questInprogress.getObjectives().get(ia).getProgress() / questInprogress.getObjectives().get(ia).getMaxProgress() * 100, guiLeft - 155, guiTop - 20 + (ia * 20), 16777215);
                    if (questInprogress.getObjectives().get(ia).getProgress() >= questInprogress.getObjectives().get(ia).getMaxProgress())
                        drawString(matrixStack, font, TextFormatting.BOLD + "V", guiLeft + 25 + (questInprogress.getObjectives().get(ia).getTitle().length() * 2), guiTop - 30 + (ia * 20) + (i * 50), 16777215);
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        drawString(matrixStack, font, TextFormatting.GRAY + "QUESTS", posX + 102, posY + 30, Color.GRAY.getRGB());
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new QuestScreen());
    }
}
