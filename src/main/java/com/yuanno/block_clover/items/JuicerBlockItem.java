package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class JuicerBlockItem extends BlockItem {
    public JuicerBlockItem() {
        super(ModBlocks.JUICER.get(),
                new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS));
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.block_clover.juicer_block_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.block_clover.juicer_block"));
        }
    }
}
