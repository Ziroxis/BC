package com.yuanno.block_clover.blocks.tileentities;

import com.yuanno.block_clover.init.ModTileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModSignTileEntity extends SignTileEntity {
    public ModSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.SIGN_TILEENTITIES.get();
    }
}
