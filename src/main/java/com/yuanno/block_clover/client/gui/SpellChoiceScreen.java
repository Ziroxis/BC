package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.client.gui.button.NoTextureButton;
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
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class SpellChoiceScreen extends Screen {

    float xMouse;
    float yMouse;
    private int guiLeft;
    private int guiTop;
    private final PlayerEntity player;
    private final int xSize = 64;
    private final int ySize = 58;
    private String value = "";
    private ArrayList<Ability> abilities = new ArrayList<>();
    private IAbilityData abilityData;

    public SpellChoiceScreen(ArrayList<Ability> abilities)
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.abilities = abilities;
        this.abilityData = AbilityDataCapability.get(player);
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        int currentX = posX - 60;

        if (abilities.isEmpty())
            return;
        for (Ability ability : abilities) {
            Button button = new Button(currentX, posY + 120, 60, 20, new TranslationTextComponent(ability.getName() + ""), b -> {
                abilityData.addUnlockedAbility(player, ability);
                PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
                this.onClose();
            }, (but, matrixStack, mouseX, mouseY) -> {
                if (but.isHovered())
                    this.renderTooltip(matrixStack, ability.getDescription(), mouseX, mouseY);
            });
            this.addButton(button);
            currentX += 100;
        }


    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.renderBackground(matrixStack);



        super.render(matrixStack, x, y, f);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
    public static void open(ArrayList<Ability> attributes)
    {
        Minecraft.getInstance().setScreen(new SpellChoiceScreen(attributes));
    }
}
