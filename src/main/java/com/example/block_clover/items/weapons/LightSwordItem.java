package com.example.block_clover.items.weapons;

import com.example.block_clover.init.ModItemGroup;
import com.example.block_clover.init.ModTiers;
import com.example.block_clover.items.MagicWeapon;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class LightSwordItem extends MagicWeapon {
    public LightSwordItem() {
        super(ModTiers.WEAPON, 6, 0.5f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
}
