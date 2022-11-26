package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IObtainItemObjective
{
    boolean checkItem(ItemStack itemStack);

    int checkItems(ArrayList<ItemStack> stacks);

}
