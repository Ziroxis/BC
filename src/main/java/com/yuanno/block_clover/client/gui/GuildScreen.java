package com.yuanno.block_clover.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CKickFromGuildPacket;
import com.yuanno.block_clover.networking.client.CLeaveGuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class GuildScreen extends Screen {

    float xMouse;
    float yMouse;
    private final PlayerEntity player;
    private final ExtendedWorldData extendedWorldData;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;

    public GuildScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        extendedWorldData = ExtendedWorldData.get(player.level);

    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();


        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;



        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;


        posX += 80;
        this.addButton(new Button(posX, posY + 190, 70, 20, new TranslationTextComponent("gui.blackclover.stats.overview"), b ->
        {
            Minecraft.getInstance().setScreen(new PlayerOverviewScreen());
        }));
        posX += 240;
        this.addButton(new Button(posX, posY + 190, 70, 20, new TranslationTextComponent("gui.blackclover.guild.leave"), b ->
        {
            boolean isInGuild = extendedWorldData.getGuildWithMember(player.getUUID()) != null;
            if (isInGuild) {
                Minecraft.getInstance().setScreen(null);
                return;
            }
            if (extendedWorldData.getGuildWithMember(player.getUUID()).getViceCaptain() != null)
                extendedWorldData.getGuildWithMember(player.getUUID()).getViceCaptain().setIsCaptain(true);
            PacketHandler.sendToServer(new CLeaveGuildPacket());
            Minecraft.getInstance().setScreen(null);
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
        guildRendering(matrixStack);

        // renderEntityInInventory(posX + 190, posY + 190, 70, (float)(posX + 190) - this.xMouse, (float)(posY + 190 - 120) - this.yMouse, this.minecraft.player);

        super.render(matrixStack, x, y, f);

    }

    public void guildRendering(MatrixStack matrixStack) {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        boolean isInGuild = extendedWorldData.getGuildWithMember(player.getUUID()) != null;
        String guildName = null;
        String guildRank = null;
        if (isInGuild) {
            guildName = Objects.requireNonNull(extendedWorldData.getGuildWithMember(player.getUUID())).getName();
            if (extendedWorldData.getGuildWithMember(player.getUUID()).getCaptain().getUUID().equals(player.getUUID()))
                guildRank = "Captain";
            else if (extendedWorldData.getGuildWithMember(player.getUUID()).getViceCaptain() != null && extendedWorldData.getGuildWithMember(player.getUUID()).getViceCaptain().getUUID().equals(player.getUUID()))
                guildRank = "Vice-captain";
            else
                guildRank = "Member";
        }
        drawString(matrixStack, font, TextFormatting.GRAY + "GUILD", posX + 102, posY + 30, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "Guild: ", posX - 20, posY + 45, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "Guild rank: ", posX - 20, posY + 55, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.BOLD + "Guild members: ", posX - 20, posY + 65, Color.GRAY.getRGB());


        if (isInGuild) {
            for (int i = 0; i < Objects.requireNonNull(extendedWorldData.getGuildWithMember(player.getUUID())).getMembers().size(); i++)
            {
                drawString(matrixStack, font, TextFormatting.WHITE + extendedWorldData.getGuildWithMember(player.getUUID()).getMembers().get(i).getUsername(), posX - 20, posY + 75 + (i * 10), Color.GRAY.getRGB());
                int finalI = i;
                if (extendedWorldData.getGuildWithMember(player.getUUID()).getViceCaptain() == null && !extendedWorldData.getGuildWithMember(player.getUUID()).getCaptain().getUUID().equals(extendedWorldData.getGuildWithMember(player.getUUID()).getMembers().get(i).getUUID()))
                {
                    this.addButton(new Button(posX + 100, posY + 75 + (i * 10), 70, 20, new TranslationTextComponent("gui.blackclover.guild.promote"), button ->
                    {
                        PacketHandler.sendToServer(new CKickFromGuildPacket(extendedWorldData.getGuildWithMember(player.getUUID()).getMembers().get(finalI).getUUID()));
                    }));
                }
                if (!extendedWorldData.getGuildWithMember(player.getUUID()).getCaptain().getUUID().equals(extendedWorldData.getGuildWithMember(player.getUUID()).getMembers().get(i).getUUID()))
                {
                    this.addButton(new Button(posX, posY + 75 + (i * 10), 70, 20, new TranslationTextComponent("gui.blackclover.guild.kick"), button ->
                    {
                        PacketHandler.sendToServer(new CKickFromGuildPacket(extendedWorldData.getGuildWithMember(player.getUUID()).getMembers().get(finalI).getUUID()));
                    }));
                }
            }
            drawString(matrixStack, font, TextFormatting.GRAY + guildName, posX + 14, posY + 45, Color.GRAY.getRGB());
            drawString(matrixStack, font, TextFormatting.GRAY + guildRank, posX + 45, posY + 55, Color.GRAY.getRGB());
        }
        else
            drawString(matrixStack, font, TextFormatting.GRAY + "None", posX + 14, posY + 45, Color.GRAY.getRGB());

    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new GuildScreen());
    }
}
