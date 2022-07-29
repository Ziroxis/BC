package com.example.block_clover.items.weapons;

import com.example.block_clover.init.ModItemGroup;
import com.example.block_clover.init.ModTiers;
import com.example.block_clover.items.MagicWeapon;
import net.minecraft.item.Item;

public class DemonDestroyerItem extends MagicWeapon {

    public DemonDestroyerItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
}
