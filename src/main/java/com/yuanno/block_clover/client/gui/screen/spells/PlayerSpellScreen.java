package com.yuanno.block_clover.client.gui.screen.spells;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.client.gui.button.TexturedIconButton;
import com.yuanno.block_clover.client.gui.button.GuiButtonAttribute;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class PlayerSpellScreen extends Screen {
    private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/background.png");
    public int guiLeft;
    public int guiTop;
    int xSize = 256;
    int ySize = 256;
    GuiButtonAttribute primarySpellsSymbol;

    protected PlayerSpellScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    public void init()
    {
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        IEntityStats props = EntityStatsCapability.get(player);
        String attribute = props.getAttribute();
        ResourceLocation attributeLocation = new ResourceLocation(Main.MODID + ":textures/gui/attributes/" + BeJavapi.getResourceName(attribute) + ".png");

        posX += 120;
        /*
        Button abilitiesButton = new Button(posX, posY + 190,
                70, 20, new TranslationTextComponent("gui.abilities", "Abilities"), b ->
        {
            System.out.println("Check");
        });

         */

        TexturedIconButton spellButtons = new TexturedIconButton(attributeLocation, posX + 50, posY + 130, 32, 32, new StringTextComponent(""), (btn) ->
        {
            System.out.println("Check");
        });
        this.addButton(spellButtons);

        /*
        addButton(primarySpellsSymbol = new GuiButtonAttribute(posX, posY + 190, button -> {

            //Minecraft.getInstance().setScreen(new SpellsScreen(playerCapability.ReturnMagicAttribute()));
            System.out.println("Check");
        }));


         */
        super.init();

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        minecraft.getTextureManager().bind(MAIN_TEXTURE);
        GuiUtils.drawTexturedModalRect(matrixStack, guiLeft, guiTop + 60, 0, 0, xSize, ySize, 0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
