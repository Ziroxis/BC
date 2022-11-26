package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.IObtainItemObjective;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ObtainItemObjective<T extends Item> extends Objective implements IObtainItemObjective
{
    private Predicate<ItemStack> check = (itemStack) -> false;

    public ObtainItemObjective(String title, int count, Supplier<T> itemTarget)
    {
        super(title);
        this.setMaxProgress(count);
        this.check = (itemStack) -> itemStack.getItem() == itemTarget.get();
    }

    public ObtainItemObjective(String title, int count, Predicate<ItemStack> check)
    {
        super(title);
        this.setMaxProgress(count);
        this.check = check;
    }

    @Override
    public boolean checkItem(ItemStack stack)
    {
        return this.check.test(stack);
    }

    @Override
    public int checkItems(ArrayList<ItemStack> stacks)
    {
        return stacks.stream().filter(this::checkItem).mapToInt(stack -> stack.getCount()).sum();
    }}
