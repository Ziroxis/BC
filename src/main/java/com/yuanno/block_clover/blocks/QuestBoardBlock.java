package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.blocks.tileentities.QuestBoardTileEntity;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModTileEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenQuestBoardPacket;
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

public class QuestBoardBlock extends Block {
    // TODO create a tile entity that stores this
    List<QuestId> availableQuest = Beapi.randomQuestsFromList(ModQuests.mergedListQuestBoard, 8);


    public QuestBoardBlock()
    {
        super(Properties.of(Material.BARRIER).strength(Float.MAX_VALUE).noDrops());
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntities.QUEST_TILEENTITY.get().create();
    }

    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        // open the quest qui right here

        if (!player.level.isClientSide) {

            IQuestData questData = QuestDataCapability.get(player);
            List<QuestId> inProgressQuests = new ArrayList<>();
            for (Quest quest : questData.getInProgressQuests())
            {
                if (quest != null)
                    inProgressQuests.add(quest.getCore());
            }
            long correspondCount = availableQuest.stream()
                    .filter(element -> inProgressQuests.contains(element) && questData.getFinishedQuests().contains(element))
                    .count();
            if (correspondCount > 5)
            {
                List<QuestId> toTakeQuests = ModQuests.mergedListQuestBoard;
                toTakeQuests.removeAll(inProgressQuests);
                toTakeQuests.removeAll(questData.getFinishedQuests());
                this.availableQuest = Beapi.randomQuestsFromList(toTakeQuests, 8);
            }
            PacketHandler.sendTo(new SOpenQuestBoardPacket(availableQuest), player);
        }
        return ActionResultType.SUCCESS;
    }
}
