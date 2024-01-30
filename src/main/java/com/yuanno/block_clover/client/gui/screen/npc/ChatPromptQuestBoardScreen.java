package com.yuanno.block_clover.client.gui.screen.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.client.gui.button.TexturedIconButton;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.entities.api.BCentity;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CUpdateQuestStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class ChatPromptQuestBoardScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/decline_button.png");

    private IQuestData questData;
    private int state = 0;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    private PlayerEntity player;
    private SequencedString message = new SequencedString("", 0, 0);
    TexturedIconButton test;
    int amountDone;

    TexturedIconButton acceptButton;
    TexturedIconButton declineButton;

    public ChatPromptQuestBoardScreen(PlayerEntity player, BCentity bCentity)
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        this.questData = QuestDataCapability.get(player);
    }

    @Override
    public void init()
    {
        super.init();
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        this.loop();
        if (this.test != null)
        {
            this.addButton(test);
            this.addButton(acceptButton);
            this.addButton(declineButton);
        }
    }

    /**
     * This goes through all the questboard quests and the in progress quests
     * questboard quest == progress quest
     * done? -> reward
     */
    public void loop()
    {
        String text = "";
        boolean hasCommons = false;
        for (Quest quest : questData.getInProgressQuests())
        {
            if (quest != null && ModQuests.mergedListQuestBoard.contains(quest.getCore()))
            {
                text = "You have a quest from the questboard! Come back when it's done so I can give your reward.";
                hasCommons = true;
                if (quest.isComplete())
                {
                    text = "Good job, here is the reward!";
                    questData.addFinishedQuest(quest.getCore());
                    questData.removeInProgressQuest(quest.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(quest.getCore()));
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 200);
                    return;
                }
            }
        }
        if (!hasCommons)
        {
            text = "I am the quest board manager. Pick a quest from the quest board, complete it and then come to me for the reward! You need the items for the reward!";
        }
        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 2000);
        return;
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);


        if (!(this.state == 0)) {
            this.buttons.remove(acceptButton);
            this.buttons.remove(declineButton);
        }


        int posX = this.width / 2;
        int posY = this.height / 2;
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 125, 0, 0, 256, 256, 0);
        this.message.render(matrixStack, guiLeft - 88, guiTop + 115);
        super.render(matrixStack, x, y, f);
    }
}
