package com.yuanno.block_clover.items;

import com.yuanno.block_clover.init.ModItemGroup;
import net.minecraft.item.Item;

public abstract class ArtifactItem extends Item {

    public ArtifactItem()
    {
        super(new Properties().durability(500).tab(ModItemGroup.BLOCK_CLOVER_ARTIFACTS));
    }
}
