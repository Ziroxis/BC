package com.yuanno.block_clover.client.gui.screen.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
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
    List<Quest> availableQuest;
    IQuestData questData;
    int buttonsPerPage = 4;
    int currentPage = 0;
    int startIndex = currentPage * buttonsPerPage;
    Button advanceButton;
    Button goBackButton;

    public QuestBoardScreen(PlayerEntity player, List<Quest> availableQuest) {
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
        for (int i = 0; i < availableQuest.size(); i++)
        {
            for (int a = 0; a < questData.getFinishedQuests().size(); a++)
            {
                if (availableQuest.get(i).getTitle().equals(questData.getFinishedQuests().get(i).getTitle()))
                    availableQuest.remove(i);
            }
            for (Quest quest : questData.getInProgressQuests())
            {
                if (quest != null && quest.getTitle().equals(availableQuest.get(i).getTitle()))
                    availableQuest.remove(i);
            }
        }



        this.buttons.clear();
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        // In the init() method
        goBackButton = new Button(posX + 40, posY + 200, 20, 20, new TranslationTextComponent("<"), b -> {
            currentPage -= 1;
            startIndex = currentPage * buttonsPerPage; // Update startIndex
            init();
        });


        advanceButton = new Button(posX + 222, posY + 200, 20, 20, new TranslationTextComponent(">"), b -> {
            currentPage += 1;
            startIndex = currentPage * buttonsPerPage; // Update startIndex
            init();
        });

        Button buttonChoice1 = new Button(posX + 162, posY + 60, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
        {
            questData.addInProgressQuest(availableQuest.get(currentPage * 4));
            PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4)));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            this.availableQuest.remove(availableQuest.get(currentPage * 4));
            this.onClose();
        });
        addButton(buttonChoice1);
        if (availableQuest.size() > currentPage * 4)
            this.buttons.add(buttonChoice1);
        else
            this.buttons.remove(buttonChoice1);
        Button buttonChoice2 = new Button(posX + 162, posY + 100, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
        {
            questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 1));
            PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 1)));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            this.availableQuest.remove(availableQuest.get(currentPage * 4 + 1));
            this.onClose();
        });
        addButton(buttonChoice2);

        if (availableQuest.size() > currentPage * 4 + 1)
            this.buttons.add(buttonChoice2);
        else
            this.buttons.remove(buttonChoice2);
        Button buttonChoice3 = new Button(posX + 162, posY + 140, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
        {
            questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 2));
            PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 2)));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            this.availableQuest.remove(availableQuest.get(currentPage * 4 + 2));
            this.onClose();
        });
        addButton(buttonChoice3);

        if (availableQuest.size() > currentPage * 4 + 2)
            this.buttons.add(buttonChoice3);
        else
            this.buttons.remove(buttonChoice3);
        Button buttonChoice4 = new Button(posX + 162, posY + 180, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
        {
            questData.addInProgressQuest(availableQuest.get(currentPage * 4 + 3));
            PacketHandler.sendToServer(new CUpdateQuestStatePacket(availableQuest.get(currentPage * 4 + 3)));

            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            this.availableQuest.remove(availableQuest.get(currentPage * 4 + 3));
            this.onClose();
        });
        addButton(buttonChoice4);
        if (availableQuest.size() > currentPage * 4 + 3)
            this.buttons.add(buttonChoice4);
        else
            this.buttons.remove(buttonChoice4);



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

        if (this.currentPage > 2 && this.buttons.contains(advanceButton))
            this.buttons.remove(this.advanceButton);
        if (this.currentPage == 0 && this.buttons.contains(this.goBackButton))
            this.buttons.remove(this.goBackButton);
        if (this.currentPage < 3 && !this.buttons.contains(this.advanceButton))
            addButton(this.advanceButton);
        if (this.currentPage > 0 && !this.buttons.contains(this.goBackButton))
            addButton(this.goBackButton);


        int questsPerPage = 4; // Number of quests displayed on each page
        int startIndex = currentPage * questsPerPage; // Calculate the starting index of quests on the current page

        for (int i = 0; i < questsPerPage && (startIndex + i) < availableQuest.size(); i++) {
            Quest quest = availableQuest.get(startIndex + i);

            drawString(matrixStack, font, TextFormatting.GRAY + "Quest: " + quest.getTitle(), posX + 10, posY + 55 + i * 40, Color.GRAY.getRGB());
            drawString(matrixStack, font, TextFormatting.GRAY + "Description: " + quest.getDescription(), posX + 10, posY + 70 + i * 40, Color.GRAY.getRGB());
        }




        super.render(matrixStack, x, y, f);
    }

    public void onPageFlip()
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        int questsPerPage = 4; // Number of quests displayed on each page
        int startIndex = currentPage * questsPerPage; // Calculate the starting index of quests on the current page

        for (int i = 0; i < questsPerPage && (startIndex + i) < availableQuest.size(); i++) {
            Quest quest = availableQuest.get(startIndex + i);
            this.addButton(new Button(posX + 162, posY + 60 + i * 40, 70, 20, new TranslationTextComponent("ACCEPT"), b ->
            {
                questData.addInProgressQuest(quest);
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.availableQuest.remove(quest);
                this.onClose();
            }));

        }
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
