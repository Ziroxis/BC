package com.yuanno.block_clover.data.devil;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class DevilProvider implements ICapabilitySerializable<CompoundNBT> {

    private IDevil instance = DevilCapability.INSTANCE.getDefaultInstance();

    @Override
    public <T>LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return DevilCapability.INSTANCE.orEmpty(capability, LazyOptional.of(() -> instance));
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) DevilCapability.INSTANCE.getStorage().writeNBT(DevilCapability.INSTANCE, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT compoundNBT)
    {
        DevilCapability.INSTANCE.getStorage().readNBT(DevilCapability.INSTANCE, instance, null, compoundNBT);
    }
}
