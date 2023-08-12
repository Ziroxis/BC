package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenChatPromptScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DevilSummonerBlock extends Block {
    int amountOfSummons = 4;
    public DevilSummonerBlock()
    {
        super(Properties.of(Material.BARRIER).strength(Float.MAX_VALUE).noCollission().noDrops());
    }

    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        PacketHandler.sendTo(new SOpenDevilSummoningScreenPacket(), player);
        return ActionResultType.SUCCESS;
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
