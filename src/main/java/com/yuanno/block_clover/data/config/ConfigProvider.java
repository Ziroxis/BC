package com.yuanno.block_clover.data.config;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ConfigProvider implements ICapabilitySerializable<CompoundNBT> {

    private IConfig instance = ConfigCapability.INSTANCE.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        return ConfigCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) ConfigCapability.INSTANCE.getStorage().writeNBT(ConfigCapability.INSTANCE, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        ConfigCapability.INSTANCE.getStorage().readNBT(ConfigCapability.INSTANCE, instance, null, nbt);
    }
}
