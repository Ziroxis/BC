package com.example.block_clover.items;

import com.example.block_clover.init.ModTiers;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class LightSwordItem extends MagicWeapon {
    public LightSwordItem(Properties properties, int damage, float speed) {
        super(ModTiers.WEAPON, damage, speed, properties);
    }
}
