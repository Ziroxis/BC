package com.yuanno.block_clover.api.challenges;

import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

public class ChallengeMetaEntry<T1 extends Challenge, T2 extends Challenge, T3 extends Challenge> {

	private final RegistryObject<ChallengeCore<T1>> standardCore;
	@Nullable
	private final RegistryObject<ChallengeCore<T2>> hardCore;
	@Nullable
	private final RegistryObject<ChallengeCore<T3>> ultimateCore;
	
	public ChallengeMetaEntry(RegistryObject<ChallengeCore<T1>> standardCore, @Nullable RegistryObject<ChallengeCore<T2>> hardCore, @Nullable RegistryObject<ChallengeCore<T3>> ultimateCore) {
		this.standardCore = standardCore;
		this.hardCore = hardCore;
		this.ultimateCore = ultimateCore;
	}
	
	public ChallengeCore<T1> getStandardChallenge() {
		return this.standardCore.get();
	}

	@Nullable
	public ChallengeCore<T2> getHardChallenge() {
		if (this.hardCore == null) {
			return null;
		}
		return this.hardCore.get();
	}
	
	@Nullable
	public ChallengeCore<T3> getUltimateChallenge() {
		if (this.ultimateCore == null) {
			return null;
		}
		return this.ultimateCore.get();
	}
}
