package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ArtifactItem extends Item {

    public String artifactInformation;

    public ArtifactItem()
    {
        super(new Properties().durability(2000).tab(ModItemGroup.BLOCK_CLOVER_ARTIFACTS));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent(this.artifactInformation));
        } else {
            tooltip.add(new TranslationTextComponent("Hold " + "§dSHIFT" + " §ffor more Information!"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }

}
