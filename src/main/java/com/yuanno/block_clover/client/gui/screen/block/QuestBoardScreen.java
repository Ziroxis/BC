package com.yuanno.block_clover.client.gui.screen.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncQuestDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.client.CUpdateQuestStatePacket;
import com.yuanno.block_clover.networking.server.SummonDevilEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class QuestBoardScreen extends Screen {

    private PlayerEntity player;
    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/background.png");
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    List<QuestId> availableQuest;
    IQuestData questData;
    int buttonsPerPage = 4;
    int currentPage = 0;
    int startIndex = currentPage * buttonsPerPage;
    Button advanceButton;
    Button goBackButton;

    public QuestBoardScreen(PlayerEntity player, List<QuestId> availableQuest) {
        super(new StringTextComponent(""));
        this.player = player;
        this.entityStats = EntityStatsCapability.get(player);
        this.questData = QuestDataCapability.get(player);
        this.availableQuest = availableQuest;
    }

    @Override
    public void init()
    {
        super.init();
        // do not include the quests that the player already has done or is progressing in
        availableQuest.removeAll(questData.getFinishedQuests());
        for (Quest quest : questData.getInProgressQuests())
        {
            if (quest != null && availableQuest.contains(quest.getCore()))
                availableQuest.remove(quest.getCore());
        }



        this.buttons.clear();
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        // In the init() method
        goBackButton = new Button(posX + 40, posY + 200, 20, 20, new TranslationTextComponent("<"), b -> {
            if (this.currentPage == 1)
                currentPage -= 1;
            startIndex = currentPage * buttonsPerPage; // Update startIndex
            init();
        });
        if (this.currentPage == 1) {
            addButton(goBackButton);

        }

        // TODO fix this issue
        advanceButton = new Button(posX + 222, posY + 200, 20, 20, new TranslationTextComponent(">"), b -> {
            if (this.currentPage == 0)
                currentPage += 1;
            startIndex = currentPage * buttonsPerPage; // Update startIndex
            init();
        });
        if (this.currentPage == 0)
            addButton(advanceButton);
        boolean flag = questData.getInProgressQuests().length == 3;
        if (availableQuest.size() > currentPage * 4 && flag)
        {
            Button buttonChoice1 = new Button(posX + 162, posY + 60, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
            {
                questData.addInProgressQuest(availableQuest.get(currentPage * 4).createQuest());
                //PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4)));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.availableQuest.remove(availableQuest.get(currentPage * 4));
                this.onClose();
            });
            this.addButton(buttonChoice1);
        }

        if (availableQuest.size() > currentPage * 4 + 1 && flag)
        {
            Button buttonChoice2 = new Button(posX + 162, posY + 100, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
            {
                questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 1).createQuest());
                //PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 1)));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.availableQuest.remove(availableQuest.get(currentPage * 4 + 1));
                this.onClose();
            });
            this.addButton(buttonChoice2);
        }


        if (availableQuest.size() > currentPage * 4 + 2 && flag)
        {
            Button buttonChoice3 = new Button(posX + 162, posY + 140, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
            {
                questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 2).createQuest());
                //PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 2)));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.availableQuest.remove(availableQuest.get(currentPage * 4 + 2));
                this.onClose();
            });
            this.addButton(buttonChoice3);
        }

        if (availableQuest.size() > currentPage * 4 + 3 && flag)
        {
            Button buttonChoice4 = new Button(posX + 162, posY + 180, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
            {
                questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 3).createQuest());
                //PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 3)));

                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.availableQuest.remove(availableQuest.get(currentPage * 4 + 3));
                this.onClose();
            });
            //this.buttons.add(buttonChoice4);
        }





        this.loop(posX, posY);
    }



    public void loop(int posX, int posY)
    {

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        minecraft.getTextureManager().bind(background);
        GuiUtils.drawTexturedModalRect(posX, posY + 30, 0, 0, 256, 256, 0);


        int questsPerPage = 4; // Number of quests displayed on each page
        int startIndex = currentPage * questsPerPage; // Calculate the starting index of quests on the current page

        for (int i = 0; i < questsPerPage && (startIndex + i) < availableQuest.size(); i++) {
            Quest quest = availableQuest.get(startIndex + i).createQuest();

            drawString(matrixStack, font, TextFormatting.GRAY + "Quest: " + quest.getCore().getName(), posX + 10, posY + 35 + i * 40, Color.GRAY.getRGB());
            drawString(matrixStack, font, TextFormatting.GRAY + "Description: ", posX + 10, posY + 43 + i * 40, Color.GRAY.getRGB());
            drawString(matrixStack, font, TextFormatting.GRAY + "" + quest.getDescription(), posX + 10, posY + 52 + i * 40, Color.GRAY.getRGB());
            drawString(matrixStack, font, TextFormatting.GRAY + "Rank: " + quest.getRank(), posX + 10, posY + 61 + i * 40, Color.GRAY.getRGB());

        }




        super.render(matrixStack, x, y, f);
    }


    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(null);
    }
}
