package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
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
public class DevilSummoningScreen extends Screen {

    private PlayerEntity player;
    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/background.png");
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;


    public DevilSummoningScreen(PlayerEntity player) {
        super(new StringTextComponent(""));
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

        this.addButton(new Button(posX + 92, posY + 120, 70, 20, new TranslationTextComponent("gui.blackclover.stats.summoning"), b ->
        {
            player.setPos(player.getX() + 5, player.getY(), player.getZ());
            summonDevil(player, this.entityStats);
        }));
        this.loop(posX, posY);
    }

    public void summonDevil(PlayerEntity player, IEntityStats entityStats) {
            int levels = entityStats.getLevel();
            List<String> availableDevils = new ArrayList<>();

            if (levels < 20) {
                availableDevils.addAll(Arrays.asList(ModValues.WALGNER));
            } else if (levels >= 20 && levels < 35) {
                availableDevils.addAll(Arrays.asList(/* Mid-ranking devils here */));
            } else if (levels >= 35) {
                availableDevils.addAll(Arrays.asList(ModValues.NAHAMAN, ModValues.LILITH));
            }

            availableDevils.removeAll(entityStats.getControlledDevilList());
            if (availableDevils.isEmpty()) {
                System.out.println("Outta devils!"); // Chat prompt that tells them the pool is empty
                return;
            }

            String devilSummoning = availableDevils.get(new Random().nextInt(availableDevils.size()));
            spawnDevil(player, devilSummoning);
        }

        private void spawnDevil(PlayerEntity player, String devilType) {
            World world = player.level;

            switch (devilType) {
                case ModValues.WALGNER:
                    PacketHandler.sendToServer(new SummonDevilEntityPacket(ModEntities.WALGNER_DEVIL.getId()));
                    break;
                case ModValues.LILITH:
                    PacketHandler.sendToServer(new SummonDevilEntityPacket(ModEntities.LILITH_DEVIL.getId()));
                    break;
                case ModValues.NAHAMAN:
                    PacketHandler.sendToServer(new SummonDevilEntityPacket(ModEntities.NAHAMAN_DEVIL.getId()));
                    break;
                default:
                    // Unknown devil type - do nothing or handle the situation accordingly
                    break;
            }
            Minecraft.getInstance().setScreen(null);
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
        drawString(matrixStack, font, TextFormatting.BOLD + "Do you want to summon a devil?", posX - 96, posY - 60, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "You'll lose everything if killed.", posX - 96, posY - 50, Color.GRAY.getRGB());

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
