package com.yuanno.block_clover.client.gui.screen.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.client.gui.button.TexturedIconButton;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.entities.BCentity;
import com.yuanno.block_clover.entities.NPCentity;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncQuestDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.client.CUpdateQuestStatePacket;
import com.yuanno.block_clover.spells.sword.OriginalDemonSlayerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDestroyerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDwellerAbility;
import com.yuanno.block_clover.spells.sword.OriginalSlashesAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.ArrayList;
import java.util.Arrays;

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
        for (Quest quest : questData.getInProgressQuests())
        {
            if (ModQuests.mergedListQuestBoard.contains(quest.getCore()))
            {
                if (quest.isComplete())
                {
                    String text = "Good job, here is the reward!";
                    questData.addFinishedQuest(quest.getCore());
                    questData.removeInProgressQuest(quest.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(quest.getCore()));
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 200);
                    return;
                }
                String text = "You got a quest from the quest board!";
                this.message = new SequencedString(text, 245, this.font.width(text) / 2, 2000);
                return;
            }
        }
        /*
        for (int ia = 0; ia < questArrayListQuestBoard.size(); ia++)
        {
            for (int i = 0; i < questData.getInProgressQuests().length; i++)
            {
                if (questData.getInProgressQuest(i) != null && questData.getInProgressQuest(i).getId().equals(questArrayListQuestBoard.get(ia).getId())) {
                    if (questData.getInProgressQuest(i).isComplete())
                    {
                        //questData.getInProgressQuest(i).triggerCompleteEvent(player);
                        questData.addFinishedQuest(questArrayListQuestBoard.get(ia).getCore());
                        questData.removeFinishedQuest(questArrayListQuestBoard.get(ia));
                        questData.removeInProgressQuest(questArrayListQuestBoard.get(ia)); // -> doesn't work
                        PacketHandler.sendToServer(new CUpdateQuestStatePacket(questArrayListQuestBoard.get(ia)));
                        String text = "Good job on the quest!";
                        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 2000);
                        return;
                    }
                    else
                    {
                        String text = "Oh you got a quest from the quest board!";
                        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 2000);
                        return;
                    }
                }
            }

        }

         */

        String text = "I manage all the quests for the guilds, pick up a quest from the quest board and once done come to me and I'll give the reward!";
        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 2000); // -> first time talking to the npc
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
