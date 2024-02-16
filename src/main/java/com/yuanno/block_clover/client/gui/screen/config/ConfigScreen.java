package com.yuanno.block_clover.client.gui.screen.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.client.gui.AttributeChoiceScreen;
import com.yuanno.block_clover.client.gui.screen.guild.GuildScreen;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerAchievementsScreen;
import com.yuanno.block_clover.client.gui.screen.overview.PlayerOverviewScreen;
import com.yuanno.block_clover.client.gui.screen.overview.QuestScreen;
import com.yuanno.block_clover.client.gui.screen.spells.SelectHotbarAbilitiesScreen;
import com.yuanno.block_clover.data.config.ConfigCapability;
import com.yuanno.block_clover.data.config.IConfig;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncConfigPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static net.minecraft.client.gui.screen.inventory.InventoryScreen.renderEntityInInventory;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;

    public ConfigScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        this.buttons.clear();
        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        IConfig config = ConfigCapability.get(player);
        String text = "";
        if (config.getPickUpItems())
            text = "Keys.block_clover.config.enabled";
        else
            text = "Keys.block_clover.config.disabled";
        posX += 80;
        this.addButton(new Button(posX + 120, posY + 26, 70, 20, new TranslationTextComponent(text), b ->
        {
            config.setPickUpItems(!config.getPickUpItems());
            PacketHandler.sendToServer(new CSyncConfigPacket(config));
            init();
        }));
    }


    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.xMouse = (float)x;
        this.yMouse = (float)y;

        this.renderBackground(matrixStack);
        drawString(matrixStack, font, TextFormatting.WHITE+ "Pick up items in combat mode:", posX - 60, posY + 32, Color.GRAY.getRGB());

        super.render(matrixStack, x, y, f);
    }



    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new ConfigScreen());
    }
}
