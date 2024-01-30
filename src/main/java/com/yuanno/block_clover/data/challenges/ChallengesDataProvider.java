package com.yuanno.block_clover.data.challenges;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ChallengesDataProvider implements ICapabilitySerializable<CompoundNBT>
{
	private IChallengesData instance = ChallengesDataCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return ChallengesDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, instance, null, nbt);
	}
}
