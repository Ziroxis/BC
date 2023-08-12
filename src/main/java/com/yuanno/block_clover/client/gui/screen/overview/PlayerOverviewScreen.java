package com.yuanno.block_clover.client.gui.screen.overview;

import com.yuanno.block_clover.client.gui.screen.guild.GuildScreen;
import com.yuanno.block_clover.client.gui.screen.spells.SelectHotbarAbilitiesScreen;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.mojang.blaze3d.matrix.MatrixStack;
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

import static net.minecraft.client.gui.screen.inventory.InventoryScreen.renderEntityInInventory;

@OnlyIn(Dist.CLIENT)
public class PlayerOverviewScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;

    public PlayerOverviewScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;




        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        posY += 210;
        posX += 40;
        this.addButton(new Button(posX, posY, 70, 20, new TranslationTextComponent("gui.blackclover.stats.spells"), b ->
        {
            Minecraft.getInstance().setScreen(new SelectHotbarAbilitiesScreen(this.player));
        }));
        posX += 240;
        this.addButton(new Button(posX + 80, posY, 70, 20, new TranslationTextComponent("gui.blackclover.stats.achievements"), b ->
        {
            Minecraft.getInstance().setScreen(new PlayerAchievementsScreen());
        }));

        //posX += 240;
        posY -= 27;
        this.addButton(new Button(posX + 80, posY, 70, 20, new TranslationTextComponent("gui.blackclover.stats.quests"), b ->
        {
           Minecraft.getInstance().setScreen(new QuestScreen());
        }));
        posX -= 240;
        this.addButton(new Button(posX, posY, 70, 20, new TranslationTextComponent("gui.blackclover.stats.guild"), b ->
        {
            Minecraft.getInstance().setScreen(new GuildScreen());
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
        statsRendering(matrixStack);

        renderEntityInInventory(posX + 128, posY + 230, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);

        super.render(matrixStack, x, y, f);
    }

    public void statsRendering(MatrixStack matrixStack)
    {
        PlayerEntity player = this.getMinecraft().player;
        IEntityStats props = EntityStatsCapability.get(player);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        String name = player.getName().getString();
        String race = props.getRace();
        String attribute = props.getAttribute();
        String secondAttribute = props.getSecondAttribute();
        int level = props.getLevel();
        int experience = props.getExperience();
        int maxExperience = props.getMaxExperience();
        int maxHealth = (int) player.getMaxHealth();
        int maxMana = (int) props.getMaxMana();
        int manaRegeneration = (int) props.getManaRegeneration();

        int leftShift = posX - 75;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, leftShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, leftShift, posY + 40, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Experience: " + TextFormatting.RESET + experience + "/" + maxExperience, leftShift, posY + 60, -1);

        int rightShift = posX + 200;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Max health: " + TextFormatting.RESET + maxHealth, rightShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Magic level: " + TextFormatting.RESET + level, rightShift, posY + 40, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Magic level: " + TextFormatting.RESET + level, rightShift, posY + 40, -1);
        if (props.hasSecondAttribute())
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Attribute: " + TextFormatting.RESET + attribute + ", " + secondAttribute, rightShift, posY + 60, -1);
        else
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Attribute: " + TextFormatting.RESET + attribute, rightShift, posY + 60, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Max mana: " + TextFormatting.RESET + maxMana, rightShift, posY + 80, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Mana regeneration: " + TextFormatting.RESET + manaRegeneration + "/s", rightShift, posY + 100, -1);


    }


    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new PlayerOverviewScreen());
    }
}
