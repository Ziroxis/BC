package com.yuanno.block_clover.client.gui.screen.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.SequencedString;
import com.yuanno.block_clover.api.ability.Ability;
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
import com.yuanno.block_clover.networking.client.COpenPlayerScreenPacket;
import com.yuanno.block_clover.networking.client.COpenSpellChoiceScreenPacket;
import com.yuanno.block_clover.networking.client.CSyncQuestDataPacket;
import com.yuanno.block_clover.networking.client.CUpdateQuestStatePacket;
import com.yuanno.block_clover.spells.water.CurrentOfTheFortuneRiverAbility;
import com.yuanno.block_clover.spells.water.HolyFistOfLoveAbility;
import com.yuanno.block_clover.spells.water.WaterSubstituteSpell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/decline_button.png");

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
    }

    @Override
    public void init()
    {
        super.init();
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        //this.loop();
        IQuestData questData = QuestDataCapability.get(player);
        if (!(questData.getInProgressQuests().length == 0))
        {
            for (Quest quest : questData.getInProgressQuests()) {
                if (quest.getCategory() != null && quest.getCategory() != Quest.Category.MAGICIAN)
                    return;
                inprogressQuestMana = quest;
                break;
            }
        }
        // check first quest
        if (inprogressQuestMana == null)
        {
            text = "You've undergone thorough basic mana training, I can't teach you more!";
            this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
            return;
        }
        if (inprogressQuestMana.getCore().equals(ModQuests.GRIMOIRE))
        {
            // edge cases
            if (entityStats.getAttribute().equals(ModValues.ANTIMAGIC))
            {
                if (inprogressQuestMana.isComplete()) {
                    text = "You don't have a shred of mana! I can't teach you anything...";
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                } else {
                    text = "Huh? An old dusty grimoire has chosen you as it's master";
                    inprogressQuestMana.triggerCompleteEvent(player);
                    questData.addFinishedQuest(inprogressQuestMana.getCore());
                    questData.removeInProgressQuest(inprogressQuestMana.getCore());
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                    PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                    this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                    return;
                }
            }
            else if (entityStats.getAttribute().equals(ModValues.SWORD) || entityStats.getSecondAttribute().equals(ModValues.SWORD))
            {
                text = "You're a prodigy! This 4 leaf grimoire just chose you!";
                inprogressQuestMana.triggerCompleteEvent(player);
                questData.addFinishedQuest(inprogressQuestMana.getCore());
                questData.removeInProgressQuest(inprogressQuestMana.getCore());
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                return;
            }
            else if (!inprogressQuestMana.isComplete()) { // if it isn't complete
                text = "You need to mature before getting your grimoire! (hit level 5)";
                this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                return;
            } else if (inprogressQuestMana.isComplete()) { // if it is complete
                questData.addFinishedQuest(inprogressQuestMana.getCore());
                questData.removeInProgressQuest(inprogressQuestMana.getCore());
                questData.addInProgressQuest(ModQuests.MANA_REINFORCEMENT.createQuest());
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                text = "A grimoire has chosen you and you unlocked a spell, go into combat mode to see your grimoire!";
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
                questData.addInProgressQuest(ModQuests.MANA_SKIN.createQuest());
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(inprogressQuestMana.getCore()));
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                text = "Mana reinforcement envelops your arm with mana dealing more damage! Use mana reinforcement for 2 minutes for your next spell.";
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
                PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
                text = "You've undergone thorough basic mana training, I can't teach you more!";
                this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
                return;
            }
        }
        else
        {
            text = "You've undergone thorough basic mana training, I can't teach you more!";
            this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
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
    private static final ArrayList<Ability> waterSpells = new ArrayList<>();

    static {
        waterSpells.add(WaterSubstituteSpell.INSTANCE.createAbility());
        waterSpells.add(CurrentOfTheFortuneRiverAbility.INSTANCE.createAbility());
        waterSpells.add(HolyFistOfLoveAbility.INSTANCE.createAbility());
    }
    @Override
    public void onClose()
    {
        if (this.text.equals("A grimoire has chosen you and you unlocked a spell, go into combat mode to see your grimoire!"))
            PacketHandler.sendToServer(new COpenSpellChoiceScreenPacket(waterSpells));
        super.onClose();
    }
}