package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;

public class ModWoodTypes {
    public static final WoodType ELDER =
            WoodType.create(new ResourceLocation(Main.MODID, "elder").toString());
    public static final WoodType MOGURO =
            WoodType.create(new ResourceLocation(Main.MODID, "moguro").toString());
}
