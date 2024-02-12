
package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModTileEntities;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenpacket;
import com.yuanno.block_clover.networking.server.SOpenQuestBoardPacket;
import com.yuanno.block_clover.networking.server.SSyncChallengeDataPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DevilAltarBlock extends Block {


    public DevilAltarBlock()
    {
        super(Properties.of(Material.BARRIER).strength(Float.MAX_VALUE).noDrops());
    }



    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        // open the quest qui right here

        if (player.level.isClientSide)
            return ActionResultType.PASS;
        PacketHandler.sendTo(new SOpenDevilSummoningScreenpacket(), player);

        return ActionResultType.SUCCESS;
    }
}
