package com.yuanno.block_clover.api.data;

import net.minecraft.nbt.CompoundNBT;

public interface IExtraUpdateData {
    public CompoundNBT getExtraData();

    public void setExtraData(CompoundNBT nbt);
}
