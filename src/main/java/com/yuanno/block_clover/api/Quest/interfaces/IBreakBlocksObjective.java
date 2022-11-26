package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public interface IBreakBlocksObjective {

    boolean checkBreakBlock(PlayerEntity player, Block block);
}
