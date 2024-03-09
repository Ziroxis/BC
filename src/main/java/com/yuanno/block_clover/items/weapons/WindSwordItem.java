package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;

public class WindSwordItem extends MagicWeapon {
    public WindSwordItem() {
        super(ModTiers.WEAPON, 6, 0.5f, new Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
}
