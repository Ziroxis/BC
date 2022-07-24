package com.example.block_clover.data.quest.objectives;

import net.minecraft.item.ItemStack;

public interface IObtainItemObjective
{
    boolean checkItem(ItemStack itemStack);
}
