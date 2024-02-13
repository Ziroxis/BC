package com.yuanno.block_clover.client.gui.screen.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.client.gui.button.TexturedIconButton;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
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
public class ChatPromptScreen extends Screen {

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
    String text = "";
    TexturedIconButton test;
    Quest inprogressQuestMana;
    private IEntityStats entityStats;
    TexturedIconButton acceptButton;
    TexturedIconButton declineButton;

    public ChatPromptScreen(PlayerEntity player)
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
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
        this.loop();

    }

    public void loop()
    {
        // check if you got a magician quest in progress
        boolean containsManaQuest = false;

        for (Quest quest : questData.getInProgressQuests())
        {
            if (quest != null
                    && ModQuests.MAGICIAN.contains(quest.getCore())) {
                containsManaQuest = true;
                inprogressQuestMana = quest;
                break;
            }
        }

        if (containsManaQuest) // if he has an in progress quest from the magician
        {
            if (inprogressQuestMana.getCore().equals(ModQuests.GRIMOIRE)) // got the grimoire quest in progress
            {
                if (entityStats.getAttribute().equals(ModValues.ANTIMAGIC)) // if anti-magic
                {
                    IDevil devil = DevilCapability.get(player);
                    devil.alterMaxDevilMana(200);
                    devil.alterDevilMana(200);
                    text = "Huh? An old dusty grimoire has chosen you as it's master";
                    inprogressQuestMana.triggerCompleteEvent(player);
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
                if (entityStats.getAttribute().equals(ModValues.SWORD) || entityStats.getSecondAttribute().equals(ModValues.SWORD))
                {
                    text = "You're a prodigy! This 4 leaf grimoire just chose you!";
                    inprogressQuestMana.triggerCompleteEvent(player);
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
                if (!inprogressQuestMana.isComplete()) { // if it isn't complete
                    text = "You need to mature before getting your grimoire! (hit level 5)";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                } else if (inprogressQuestMana.isComplete()) { // if it is complete
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    text = "Good job! A grimoire has chosen you, use a spell to open the grimoire!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else if (inprogressQuestMana.getCore().equals(ModQuests.MANA_REINFORCEMENT))
            {
                if (!inprogressQuestMana.isComplete()) {
                    text = "You need to get stronger before unlocking mana reinforcement! (hit level 10) And come back!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                } else if (inprogressQuestMana.isComplete())
                {
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    text = "Mana reinforcement envelops your arm with mana dealing more damage! Come back when you're stronger to start your training again.";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else if (inprogressQuestMana.getCore().equals(ModQuests.MANA_SKIN))
            {
                if (!inprogressQuestMana.isComplete()) {
                    text = "How is the training going? You need to use mana reinforcement for 2 minutes as training. After that come back to me!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                } else if (inprogressQuestMana.isComplete())
                {
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    text = "You've undergone thorough training, now comes the sensing your surroundings";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else if (inprogressQuestMana.getCore().equals(ModQuests.MANA_SENSE))
            {
                if (!inprogressQuestMana.isComplete()) {
                    text = "How is the training going? You need to use mana skin for 2 minutes as training. After that come back to me!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                } else if (inprogressQuestMana.isComplete())
                {
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    text = "You've completed my training";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
        }
        else
        {
            if (questData.getFinishedQuests().contains(ModQuests.GRIMOIRE))
            {
                if (entityStats.getAttribute().equals(ModValues.ANTIMAGIC))
                {
                    text = "You have no mana at all there is nothing for me to teach you...";
                    this.message = new SequencedString(text, 245, this.font.width(text), 800);
                    return;
                }
                questData.addInProgressQuest(ModQuests.MANA_REINFORCEMENT.createQuest());
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(ModQuests.MANA_REINFORCEMENT));
                text = "Now you have your grimoire, reach a higher level and come back so I can teach you mana reinforcement! (hit level 10)";
                this.message = new SequencedString(text, 245, this.font.width(text), 800);
                return;
            }
            else if (questData.getFinishedQuests().contains(ModQuests.MANA_REINFORCEMENT))
            {
                if (entityStats.getLevel() < 20)
                {
                    text = "You have to become stronger for me to teach you mana skin! (hit level 20)";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
                else
                {
                    questData.addInProgressQuest(ModQuests.MANA_SKIN.createQuest());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(ModQuests.MANA_SKIN));
                    text = "Here is my training regiment, stand still using mana reinforcement for 2 minutes and then come back!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else if (questData.getFinishedQuests().contains(ModQuests.MANA_SKIN))
            {
                if (entityStats.getLevel() < 25)
                {
                    text = "You have to become stronger for me to teach you mana sense! (hit level 25)";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
                else
                {
                    questData.addInProgressQuest(ModQuests.MANA_SENSE.createQuest());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(ModQuests.MANA_SENSE));
                    text = "You're now ready to learn mana sense, here is my training regiment use mana skin for 2 minutes and then come back to me!";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else {
                text = "My training regiment ends here, come back later maybe for more stuff!";
                this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                return;
            }
        }

    }

    void questInprogressLoop(Quest questInProgress, String ifNotCompleted, String ifCompleted) // TODO finish up this loop later
    {
        if (!questInProgress.isComplete())
        {
            this.message = new SequencedString(text, 245, this.font.width(text) / 2, 200);
            return;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);

        int posX = this.width / 2;
        int posY = this.height / 2;
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 125, 0, 0, 256, 256, 0);
        this.message.render(matrixStack, guiLeft - 88, guiTop + 115);
        super.render(matrixStack, x, y, f);
    }
}