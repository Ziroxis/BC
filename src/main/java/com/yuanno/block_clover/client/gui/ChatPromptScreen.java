package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.entities.NPCentity;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CUpdateQuestStatePacket;
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

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/decline_button.png");

    private IQuestData questData;
    private int state = 0;
    private NPCentity npCentity;
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

    public ChatPromptScreen(PlayerEntity player, NPCentity npCentity)
    {
        super(new StringTextComponent(""));
        this.npCentity = npCentity;
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
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.loop(posX, posY);
        if (this.test != null)
        {
            this.addButton(test);
            this.addButton(acceptButton);
            this.addButton(declineButton);
        }
    }

    public void loop(int posX, int posY)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        ArrayList<String> alreadyDoneQuestID = new ArrayList<String>();
        ArrayList<String> inProgressQuestID = new ArrayList<String>();
        int amountDone = 0;
        for (int i = 0; i < questData.getFinishedQuests().size(); i++)
        {
            if (!alreadyDoneQuestID.contains(questData.getFinishedQuests().get(i).getId()))
            {
                alreadyDoneQuestID.add(questData.getFinishedQuests().get(i).getId());
            }
        }

        for (int i = 0; i < questData.getInProgressQuests().length; i++) {
            /*
            if (!questData.getInProgressQuest(i).getId().isEmpty() && inProgressQuestID.isEmpty() || !inProgressQuestID.contains(questData.getInProgressQuest(i).getId())) {
                inProgressQuestID.add(questData.getInProgressQuest(i).getId());
            }

             */
            if (questData.getInProgressQuest(i) != null)
            {
                if (!questData.getInProgressQuest(i).getId().isEmpty()) {
                    if (inProgressQuestID.isEmpty()) {
                        if (!inProgressQuestID.contains(questData.getInProgressQuest(i).getId()))
                            inProgressQuestID.add(questData.getInProgressQuest(i).getId());
                    }
                }
            }
        }
        ArrayList<String> alreadyDoneQuestNPCID = new ArrayList<String>();
        for (int i = 0; i < npCentity.quests.size(); i++)
        {
            if (!alreadyDoneQuestNPCID.contains(npCentity.quests.get(i).getId()))
                alreadyDoneQuestNPCID.add(npCentity.quests.get(i).getId());
        }
        for (String s : alreadyDoneQuestID) {
            if (alreadyDoneQuestNPCID.contains(s))
                amountDone++;
        }
        int finalAmountDone = amountDone;
        Quest questGiver = npCentity.quests.get(finalAmountDone);
        /*
        for(int i = 0; i < questData.getInProgressQuests().length; i++)
        {
            System.out.println(questData.getInProgressQuest(i));
            if (questData.getInProgressQuest(i) != null && !questData.hasFinishedQuest(questData.getInProgressQuest(i)) && alreadyDoneQuestNPCID.contains(questData.getInProgressQuest(i).getId()) && questData.getInProgressQuest(i).isComplete())
            {
                for (int ia = 0; ia < npCentity.quests.size(); i++)
                {
                    if (npCentity.quests.get(ia) != null && npCentity.quests.get(ia).getTitle().equals(questData.getInProgressQuest(i).getTitle()))
                    {
                        Quest quest = npCentity.quests.get(ia);
                        questData.addFinishedQuest(quest);
                        questData.removeFinishedQuest(quest);
                        questData.removeInProgressQuest(quest);
                        PacketHandler.sendToServer(new CUpdateQuestStatePacket(quest));
                        this.message = new SequencedString(npCentity.doneSpeech + "", 245, this.font.width(npCentity.doneSpeech) / 2, 2000); // -> first time talking to the npc
                    }
                }
                return;
            }
        }
        */

        for (int i = 0; i < questData.getInProgressQuests().length; i++)
        {
            if (questData.getInProgressQuest(i) != null)
            {
                for (int ia = 0; ia < npCentity.quests.size(); ia++)
                {
                    if (npCentity.quests.get(ia) != null && questData.getInProgressQuest(i).getId().equals(npCentity.quests.get(ia).getId()))
                    {
                        if (npCentity.quests.get(ia) != null && !questData.hasFinishedQuest(npCentity.quests.get(ia)) && npCentity.quests.get(ia).isComplete())
                        {
                            questData.addFinishedQuest(npCentity.quests.get(ia));
                            questData.removeFinishedQuest(npCentity.quests.get(ia));
                            questData.removeInProgressQuest(npCentity.quests.get(ia));
                            PacketHandler.sendToServer(new CUpdateQuestStatePacket(npCentity.quests.get(ia)));
                            this.message = new SequencedString(npCentity.doneSpeech + "", 245, this.font.width(npCentity.doneSpeech) / 2, 2000); // -> first time talking to the npc
                            return;
                        }
                        else
                        {
                            this.message = new SequencedString(npCentity.waitingSpeech + "", 245, this.font.width(npCentity.doneSpeech) / 2, 2000); // -> first time talking to the npc
                            return;
                        }
                    }
                }
            }
            else
                break;
        }

        if (npCentity.preRequisite)
        {
            if (npCentity.requisite == 1 && entityStats.getLevel() < npCentity.levelrequisites.get(amountDone))
            {
                this.message = new SequencedString(npCentity.requisiteSpeech + "", 245, this.font.width(npCentity.requisiteSpeech) / 2, 20000);
                return;
            }
            else if (npCentity.requisite == 2 && !alreadyDoneQuestID.contains(npCentity.questRequisite.getId()))
            {
                this.message = new SequencedString(npCentity.requisiteSpeech + "", 245, this.font.width(npCentity.requisiteSpeech) / 2, 20000);
                return;
            }
        }
        if (!inProgressQuestID.contains(npCentity.quests.get(amountDone).getId()) && !alreadyDoneQuestID.contains(npCentity.quests.get(amountDone).getId()))
        {
            this.message = new SequencedString(npCentity.questChoiceSpeech + "", 245, this.font.width(npCentity.questChoiceSpeech) / 2, 2000); // -> first time talking to the npc
            test = new TexturedIconButton(acceptButtonTexture, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
            {
                //just a button to remove the giant black square
            });
            acceptButton = new TexturedIconButton(acceptButtonTexture, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.questData.addInProgressQuest(questGiver);
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(questGiver));
                this.state = 1;
                this.message = new SequencedString(npCentity.acceptanceSpeech + "", 245, this.font.width(npCentity.declineSpeech) / 2, 2000);
            }); // -> accepting the quest
            declineButton = new TexturedIconButton(declineButtonTexture, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.message = new SequencedString(npCentity.declineSpeech + "", 245, this.font.width(npCentity.declineSpeech) / 2, 2000);
                this.state = -1;
            }); // -> declining the quest

        }
        else if (inProgressQuestID.contains(npCentity.quests.get(amountDone).getId()))
        {
            this.message = new SequencedString(npCentity.waitingSpeech + "", 245, this.font.width(npCentity.questChoiceSpeech) / 2, 2000); // -> first time talking to the npc
            return;
        }
        else if (alreadyDoneQuestID.contains(npCentity.quests.get(amountDone).getId()))
        {
            this.message = new SequencedString(npCentity.doneSpeech + "", 245, this.font.width(npCentity.questChoiceSpeech) / 2, 2000); // -> first time talking to the npc
            return;
        }
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
