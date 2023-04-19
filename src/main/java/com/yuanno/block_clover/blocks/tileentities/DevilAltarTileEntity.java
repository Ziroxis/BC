package com.yuanno.block_clover.blocks.tileentities;

import com.yuanno.block_clover.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class DevilAltarTileEntity extends TileEntity implements ITickableTileEntity {

    public DevilAltarTileEntity(TileEntityType<?> tiletype) {
        super(tiletype);
    }

    public DevilAltarTileEntity() {
        this(ModTileEntities.DEVIL_ALTAR_TILEENTITY.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        return super.save(nbt);
    }

    @Override
    public void tick() {

    }
}
