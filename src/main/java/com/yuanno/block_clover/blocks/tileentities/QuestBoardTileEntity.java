package com.yuanno.block_clover.blocks.tileentities;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.List;
import java.util.Optional;

public class QuestBoardTileEntity extends TileEntity implements ITickableTileEntity {
    public QuestBoardTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public QuestBoardTileEntity()
    {
        this(ModTileEntities.QUEST_TILEENTITY.get());
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt)
    {
        super.save(nbt);
        List<QuestId> availableQuest = Beapi.randomQuestsFromList(ModQuests.mergedListQuestBoard, 8);



        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt)
    {
        super.load(state, nbt);

    }

    @Override
    public void tick() {

    }
}
