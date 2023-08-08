package com.yuanno.block_clover.client.gui.screen.guild;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.client.gui.button.NoTextureButton;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CCreateGuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GuildCreationScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    private TextFieldWidget nameEdit;

    public GuildCreationScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;



        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        this.nameEdit = new TextFieldWidget(this.font, posX + 160, posY + 160, 140, 20, new StringTextComponent(""));
        this.nameEdit.setMaxLength(20);
        this.nameEdit.insertText(this.player.getName().getString() + "'s Guild");
        this.children.add(this.nameEdit);
        this.setFocused(this.nameEdit);



    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.xMouse = (float)x;
        this.yMouse = (float)y;
        this.nameEdit.render(matrixStack, x, y, f);

        this.renderBackground(matrixStack);
        guildRendering(matrixStack);

        // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);
        String createString = "Create";
        NoTextureButton createCrewButton = new NoTextureButton(posX + 95, posY + 200, 60, 16, new TranslationTextComponent(createString), (btn) -> this.createGuild());
        this.addButton(createCrewButton);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        boolean isInGuild = extendedWorldData.getGuildWithMember(player.getUUID()) != null;

        if (isInGuild || entityStats.getLevel()<20)
        {
            createCrewButton.active = false;
        }
        super.render(matrixStack, x, y, f);

    }
    private void createGuild()
    {
        PacketHandler.sendToServer(new CCreateGuildPacket(this.nameEdit.getValue()));
        this.onClose();
    }
    public void guildRendering(MatrixStack matrixStack)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getLevel() < 20)
        {
            drawString(matrixStack, font, TextFormatting.WHITE+ "You should become stronger before creating a guild.", posX + 0, posY + 100, Color.GRAY.getRGB());
        }
        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(player.level);
        boolean isInGuild = extendedWorldData.getGuildWithMember(player.getUUID()) != null;
        if (isInGuild)
        {
            drawString(matrixStack, font, TextFormatting.WHITE+ "You're already in a guild!", posX + 60, posY + 100, Color.GRAY.getRGB());
        }
        drawString(matrixStack, font, TextFormatting.GRAY + "GUILD", posX + 102, posY + 50, Color.GRAY.getRGB());
    }
    @Override
    public void resize(Minecraft minecraft, int x, int y)
    {
        String crewName = this.nameEdit.getValue();
        this.init(minecraft, x, y);
        this.nameEdit.insertText(crewName);
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
    @Override
    public void tick()
    {
        this.nameEdit.tick();
    }
    public static void open()
    {
        Minecraft.getInstance().setScreen(new GuildCreationScreen());
    }
}
