package com.yuanno.block_clover.data.challenges;

import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class ChallengesDataCapability {
	@CapabilityInject(IChallengesData.class)
	public static final Capability<IChallengesData> INSTANCE = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IChallengesData.class, new Capability.IStorage<IChallengesData>() {
			@Override
			public INBT writeNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side) {
				CompoundNBT props = new CompoundNBT();

				ListNBT challenges = new ListNBT();
				for (int i = 0; i < instance.getChallenges().size(); i++) {
					Challenge challenge = instance.getChallenges().get(i);
					CompoundNBT nbtData = new CompoundNBT();
					nbtData.putString("id", challenge.getCore().getRegistryName().toString());
					challenge.save(nbtData);
					challenges.add(nbtData);
				}
				props.put("challenges", challenges);

				return props;
			}

			@Override
			public void readNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side, INBT nbt) {
				CompoundNBT props = (CompoundNBT) nbt;

				instance.clearChallenges();

				ListNBT challenges = props.getList("challenges", Constants.NBT.TAG_COMPOUND);
				for (int i = 0; i < challenges.size(); i++) {
					try {
						CompoundNBT nbtData = challenges.getCompound(i);
						ChallengeCore core = (ChallengeCore) GameRegistry.findRegistry(ChallengeCore.class).getValue(new ResourceLocation(nbtData.getString("id")));
						if (core == null)
							continue;
						Challenge challenge = core.createChallenge();
						challenge.load(nbtData);
						instance.addChallenge(challenge);
					}
					catch (Exception e) {
						continue;
					}
				}
			}

		}, ChallengesDataBase::new);
	}

	@Nullable
	public static IChallengesData get(final PlayerEntity entity) {
		return entity.getCapability(INSTANCE, null).orElse(null);
	}

	public static LazyOptional<IChallengesData> getLazy(final PlayerEntity entity) {
		return entity.getCapability(INSTANCE, null);
	}
}
