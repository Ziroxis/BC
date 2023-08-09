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
    List<Quest> availableQuest = ModValues.availableQuests;
    IQuestData questData;

    public QuestBoardScreen(PlayerEntity player) {
        super(new StringTextComponent(""));
        this.player = player;
        this.entityStats = EntityStatsCapability.get(player);
        this.questData = QuestDataCapability.get(player);
    }

    @Override
    public void init()
    {
        super.init();
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        for (int i = 0; i < availableQuest.size(); i++)
        {
            if (!questData.getFinishedQuests().contains(availableQuest.get(i)))
            {
                this.addButton(new Button(posX + 92, posY + 120 + i * 15, 70, 20, new TranslationTextComponent("Quest " + i), b ->
                {

                }));
                minecraft.getTextureManager().bind(background);
                GuiUtils.drawTexturedModalRect(posX - 128, posY - 100 + i * 15, 0, 0, 80, 40, 0);
            }
        }


        this.loop(posX, posY);
    }



    public void loop(int posX, int posY)
    {

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);




        int posX = this.width / 2;
        int posY = this.height / 2;
        minecraft.getTextureManager().bind(background);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 100, 0, 0, 256, 256, 0);



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
