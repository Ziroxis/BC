package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.client.gui.button.NoTextureButton;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncQuestDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.yuanno.block_clover.api.Beapi.randomAttributeString;

@OnlyIn(Dist.CLIENT)
public class AttributeChoiceScreen extends Screen {

    float xMouse;
    float yMouse;
    private int guiLeft;
    private int guiTop;
    private final PlayerEntity player;
    private final int xSize = 64;
    private final int ySize = 58;
    private String value = "";
    private ArrayList<String> attributes = new ArrayList<>();

    public AttributeChoiceScreen(ArrayList<String> attributes)
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.attributes = attributes;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        // here the randomizer comes in and chooses 3 different attributes for the player, makes sure it's never the same


        String randomAttributeString = attributes.get(0);
        String randomAttributeString2 = attributes.get(1);
        String randomAttributeString3 = attributes.get(2);

        if (randomAttributeString.equals(ModValues.ANTIMAGIC))
            randomAttributeString = "Anti magic";
        if (randomAttributeString2.equals(ModValues.ANTIMAGIC))
            randomAttributeString2 = "Anti magic";
        if (randomAttributeString3.equals(ModValues.ANTIMAGIC))
            randomAttributeString3 = "Anti magic";
        // here the buttons that show the attribute, once chosen they'll receive the ability etc
        NoTextureButton AttributeChoice1 = new NoTextureButton(posX + 25, posY + 100, 60, 16, new TranslationTextComponent(randomAttributeString),
                (btn) -> this.getAttribute(attributes.get(0)));
        NoTextureButton AttributeChoice2 = new NoTextureButton(posX + 90, posY + 100, 60, 16, new TranslationTextComponent(randomAttributeString2),
                (btn) -> this.getAttribute(attributes.get(1)));
        NoTextureButton AttributeChoice3 = new NoTextureButton(posX + 155, posY + 100, 60, 16, new TranslationTextComponent(randomAttributeString3),
                (btn) -> this.getAttribute(attributes.get(2)));
        this.addButton(AttributeChoice1);
        this.addButton(AttributeChoice2);
        this.addButton(AttributeChoice3);

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.renderBackground(matrixStack);



        super.render(matrixStack, x, y, f);
    }

    // the method that fires of a packet that gives them all their stuff
    private void getAttribute(String attribute)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IQuestData questData = QuestDataCapability.get(player);
        entityStats.setAttribute(attribute);
        Map<String, AbilityCore> abilityMap = ModValues.getAbilityMap();

        // Get the ability based on the attribute value from the HashMap
        AbilityCore ability = abilityMap.get(attribute);

        // Add the unlocked ability to the player
        if (ability != null) {
            abilityData.addUnlockedAbility(player, ability);

            // Additional operations for specific cases

        }
        if (!attribute.equals(ModValues.ANTIMAGIC)) {
            entityStats.setRace(BeJavapi.randomizer(ModValues.races));
            String race = entityStats.getRace();
            switch (race) {
                // Humans get -> 0.2 xp multiplier; chance to be innate devil; mana regen = 1
                case ModValues.HUMAN:
                    entityStats.setManaRegeneration(1);
                    entityStats.setMultiplier(1.2f);
                    /*
                    int chanceDevil = Beapi.RNG(5);
                    if (chanceDevil == 0) {
                        entityStats.setInnateDevil(true);
                        entityStats.setDevil(Beapi.randomizer(ModValues.devils));
                    }

                     */
                    break;
                // Elf get mana regeneration = 2; multiplier = 1
                case ModValues.ELF:
                    entityStats.setManaRegeneration(2);
                    entityStats.setMultiplier(1);
                    break;
                // Witch get mana regeneration = 1; xp multiplier = 0.3
                case ModValues.WITCH:
                    entityStats.setManaRegeneration(1);
                    entityStats.setMultiplier(1.3f);
                    break;
            }
        if (race.equals(ModValues.HYBRID)) {
            entityStats.setManaRegeneration(1);
            entityStats.setMultiplier(1);

            setSecondAttribute(entityStats, abilityData);
        }
        }
        if (!attribute.equals(ModValues.ANTIMAGIC))
        {
            if (!entityStats.getRace().equals(ModValues.ELF))
            {
                entityStats.setMana(50);
                entityStats.setMaxMana(50);
            }
            else
            {
                entityStats.setMaxMana(60);
                entityStats.setMana(60);
            }
        }
        else
        {
            entityStats.setRace(ModValues.HUMAN);
            entityStats.setMana(0);
            entityStats.setMaxMana(0);
        }
        questData.addInProgressQuest(ModQuests.GRIMOIRE.createQuest());
        PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        this.onClose();
    }

    public void setSecondAttribute(IEntityStats entityStats, IAbilityData abilityData)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        List<String> availableAttributesRandomizer = ModValues.availableAttributes;
        availableAttributesRandomizer.remove(entityStats.getAttribute());
        String secondAttribute = availableAttributesRandomizer.get(new Random().nextInt(availableAttributesRandomizer.size()));
        entityStats.setSecondAttribute(secondAttribute);

        Map<String, AbilityCore> abilityMap = ModValues.getAbilityMap();
        // Get the ability based on the attribute value from the HashMap
        AbilityCore ability = abilityMap.get(secondAttribute);

        // Add the unlocked ability to the player
        if (ability != null) {
            abilityData.addUnlockedAbility(player, ability);

        }
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
    public static void open(ArrayList<String> attributes)
    {
        Minecraft.getInstance().setScreen(new AttributeChoiceScreen(attributes));
    }
}
