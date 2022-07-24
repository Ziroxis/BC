package com.example.block_clover.client.gui;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class GuiButtonAttribute extends Button {

    protected final Button.IPressable onPress;

    int posX;
    int posY;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonAttribute(int posX, int posY, IPressable onPress) {
        super(posX, posY,16, 16, new StringTextComponent(""), onPress);
        this.posX = posX;
        this.posY = posY;
        this.onPress = onPress;
    }



    @Override
    public void renderButton(MatrixStack matrixStack, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        IEntityStats props = EntityStatsCapability.get(player);
        String attribute = props.getAttribute();


        mc.textureManager.bind(new ResourceLocation(Main.MODID + ":textures/gui/attributes/" + Beapi.getResourceName(attribute) + ".png"));
        if (!visible)
        {
            return;
        }
        GuiUtils.drawTexturedModalRect(matrixStack, posX, posY, 0, 0, 16, 16, 0);
    }
}
