package com.yuanno.block_clover.client.gui.screen.guild;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.client.gui.button.NoTextureButton;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CJoinGuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;


@OnlyIn(Dist.CLIENT)
public class GuildInviteScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity playerTarget;
    private final PlayerEntity playerCaptain;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;

    public GuildInviteScreen(PlayerEntity target, PlayerEntity captain)
    {
        super(new StringTextComponent(""));
        this.playerCaptain = captain;
        this.playerTarget = target;
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



    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.xMouse = (float)x;
        this.yMouse = (float)y;

        this.renderBackground(matrixStack);

        // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);
        String joinString = "Join";
        String refuseString = "Refuse";
        ExtendedWorldData extendedWorldDataCaptain = ExtendedWorldData.get(playerCaptain.level);
        drawString(matrixStack, font, TextFormatting.GRAY + "GUILD INVITATION", posX + 92, posY + 30, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "Do you want to join " + extendedWorldDataCaptain.getCrewWithCaptain(playerCaptain.getUUID()).getName(), posX + 42, posY + 130, Color.GRAY.getRGB());

        ExtendedWorldData extendedWorldData = ExtendedWorldData.get(playerTarget.level);
        boolean isInGuild = extendedWorldData.getGuildWithMember(playerTarget.getUUID()) != null;

        NoTextureButton joinGuildButton = new NoTextureButton(posX + 35, posY + 200, 60, 16, new TranslationTextComponent(joinString), (btn) -> this.joinGuild());
        this.addButton(joinGuildButton);
        NoTextureButton refuseGuildButton = new NoTextureButton(posX + 150, posY + 200, 60, 16, new TranslationTextComponent(refuseString), (btn) -> this.refuseGuild());
        this.addButton(refuseGuildButton);

        if (isInGuild)
            joinGuildButton.active = false;

        super.render(matrixStack, x, y, f);

    }

    private void joinGuild()
    {
        PacketHandler.sendToServer(new CJoinGuildPacket(playerCaptain.getUUID()));
        this.onClose();
    }

    private void refuseGuild()
    {
        // refuse guild
        this.onClose();
    }
}
