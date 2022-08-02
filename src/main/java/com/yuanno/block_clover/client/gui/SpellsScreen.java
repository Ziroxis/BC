package com.yuanno.block_clover.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpellsScreen extends Screen {
    protected SpellsScreen() {
        super(new TranslationTextComponent(""));
    }
}
