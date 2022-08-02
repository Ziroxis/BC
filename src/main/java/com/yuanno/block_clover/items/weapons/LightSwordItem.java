package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.item.Item;

public class LightSwordItem extends MagicWeapon {
    public LightSwordItem() {
        super(ModTiers.WEAPON, 6, 0.5f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
}
