package com.yuanno.block_clover.client.gui.screen.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.init.ModChallenges;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CStartChallengePacket;
import com.yuanno.block_clover.networking.server.SummonDevilEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
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
public class DevilSummoningScreen extends Screen {

    private PlayerEntity player;
    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/background.png");
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    private final IChallengesData challengesDataBase;
    private final LivingEntity[] group = new LivingEntity[1];


    public DevilSummoningScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.group[0] = this.player;

        this.entityStats = EntityStatsCapability.get(player);
        this.challengesDataBase = ChallengesDataCapability.get(player);

    }

    @Override
    public void init()
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        addChallengeButton(posX - 65, posY + 20, ModChallenges.WALGNER_DEVIL.get(), "Walgner", "Challenging Walgner, low rank devil");

    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText) {
        addChallengeButton(x, y, challenge, buttonText, tooltipText, false);
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText, boolean checkCompletion) {
        net.minecraft.client.gui.widget.button.Button button = new Button(x, y, 60, 20, new TranslationTextComponent(buttonText), b -> {
            PacketHandler.sendToServer(new CStartChallengePacket(challenge.getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (but, matrixStack, mouseX, mouseY) -> {
            if (but.isHovered()) {
                this.renderTooltip(matrixStack, new TranslationTextComponent(tooltipText), mouseX, mouseY);
            }
        });

        if (checkCompletion) {
            button.active = challengesDataBase.hasChallenge(challenge) || challengesDataBase.isChallengeCompleted(challenge);
        } else {
            button.active = challengesDataBase.hasChallenge(challenge);
        }

        this.addButton(button);
    }



    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);

        super.render(matrixStack, x, y, f);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new DevilSummoningScreen());
    }
}
