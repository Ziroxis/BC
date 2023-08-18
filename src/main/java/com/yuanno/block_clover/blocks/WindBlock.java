package com.yuanno.block_clover.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WindBlock extends Block {
    public WindBlock()
    {
        super(Properties.of(Material.BARRIER).strength(Float.MAX_VALUE).noCollission().noDrops());
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }



    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock() == this ? true : false;
    }

    @Override
    public int getLightBlock(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 0;
    }
}
