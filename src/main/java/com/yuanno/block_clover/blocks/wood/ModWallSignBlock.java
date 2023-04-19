package com.yuanno.block_clover.blocks.wood;

import com.yuanno.block_clover.blocks.tileentities.ModSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(Properties props, WoodType type) {
        super(props, type);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ModSignTileEntity();
    }
}
