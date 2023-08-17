package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenQuestBoardPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class QuestBoardBlock extends Block {
    List<QuestId> availableQuest = ModQuests.mergedListQuestBoard;


    public QuestBoardBlock()
    {
        super(Properties.of(Material.BARRIER).strength(Float.MAX_VALUE).noDrops());
    }

    public void tick()
    {
        System.out.println("test");
    }
    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        // open the quest qui right here
        if (!player.level.isClientSide)
            PacketHandler.sendTo(new SOpenQuestBoardPacket(availableQuest), player);
        return ActionResultType.SUCCESS;
    }
}
