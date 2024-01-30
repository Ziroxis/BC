package com.yuanno.block_clover.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModChallenges;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CStartChallengePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class DevilSummoningScreen extends Screen {

    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/backgrounds/background.png");
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    private final IChallengesData challengesDataBase;
    private final LivingEntity[] group = new LivingEntity[1];
    private final PlayerEntity player;
    private final ArrayList<Item> items;
    public DevilSummoningScreen() {
        super(new TranslationTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.group[0] = this.player;
        this.items = new ArrayList<>();
        this.entityStats = EntityStatsCapability.get(player);
        this.challengesDataBase = ChallengesDataCapability.get(player);

    }



    @Override
    public void init()
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        //addChallengeButton(posX - 65, posY + 20, ModChallenges.WALGNER_DEVIL.get(), "Walgner", "Challenging Walgner, low rank devil");
        for (int i = 0; i < player.inventory.getContainerSize(); i++)
        {
            if (!player.inventory.getItem(i).isEmpty())
                items.add(player.inventory.getItem(i).getItem());
        }
        ArrayList<ChallengeCore> challengeCores = new ArrayList<>();
        challengeCores.add(ModChallenges.LILITH_DEVIL.get());
        challengeCores.add(ModChallenges.NAHAMAN_DEVIL.get());
        int test = BeJavapi.randomWithRange(0, 1);
        Button button = new Button(posX - 65, posY + 20, 32, 16, new TranslationTextComponent("Highest Rank"), b -> {
            PacketHandler.sendToServer(new CStartChallengePacket(challengeCores.get(test).getRegistryName(), this.group, false));
            if (items.contains(Items.NETHER_STAR))
                items.remove(Items.NETHER_STAR);
            else if (items.contains(Items.ENCHANTED_GOLDEN_APPLE))
                items.remove(Items.ENCHANTED_GOLDEN_APPLE);
            else if (items.contains(Items.DRAGON_EGG))
                items.remove(Items.DRAGON_EGG);
            this.minecraft.setScreen(null);
        }, (but, matrixStack, mouseX, mouseY) -> {
         if (but.isHovered() && but.active)
         {
             this.renderTooltip(matrixStack, new TranslationTextComponent("Try subjugation of a random highest rank demon demon"), mouseX, mouseY);
         }
         else if (but.isHovered() && !but.active)
         {
             this.renderTooltip(matrixStack,
                     new TranslationTextComponent("Need a high quality offering for highest rank demon\nItems:\nNether Star\nEnchanted Golden Apple\nDragon egg"), mouseX, mouseY);
         }});
        button.active = items.contains(Items.NETHER_STAR) || items.contains(Items.ENCHANTED_GOLDEN_APPLE) || items.contains(Items.DRAGON_EGG);
        this.addButton(button);
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText) {
        addChallengeButton(x, y, challenge, buttonText, tooltipText, false);
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText, boolean checkCompletion) {
        Button button = new Button(x, y, 60, 20, new TranslationTextComponent(buttonText), b -> {
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
