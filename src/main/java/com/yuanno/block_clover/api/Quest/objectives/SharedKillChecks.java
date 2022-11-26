package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.ItemsHelper;
import net.minecraft.item.ItemStack;

public class SharedKillChecks
{
    public static final KillEntityObjective.ICheckKill EXISTS = ((player, target, source) ->
    {
       return player.isAlive();
    });

    public static final KillEntityObjective.ICheckKill HAS_SWORD = (player, target, source) ->
    {
        ItemStack heldItem = player.getMainHandItem();
        return ItemsHelper.isSword(heldItem);
    };

    public static final KillEntityObjective.ICheckKill HAS_EMPTY_HAND = (player, target, source) ->
    {
        ItemStack heldItem = player.getMainHandItem();
        return heldItem.isEmpty();
    };
}
