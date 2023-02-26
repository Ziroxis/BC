package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class DemonDwellerItem extends MagicWeapon {

    public DemonDwellerItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.BLOCK;
    }
}
