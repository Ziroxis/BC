package com.yuanno.block_clover.api.challenges;

import com.yuanno.block_clover.api.Beapi;
import net.minecraft.nbt.CompoundNBT;

public abstract class Challenge {
	private final ChallengeCore<?> core;
	private boolean isComplete;
	private int bestTime;

	public Challenge(ChallengeCore core) {
		this.core = core;
	}

	public String getCategory() {
		return this.core.getCategory();
	}

	public void tryUpdateBestTime(int time) {
		int pb = this.getBestTimeTick();
		if (pb > 0 && time < pb) {
			this.bestTime = time;
		}
		else if (pb == 0) {
			this.bestTime = time;
		}
	}
	
	public int getBestTimeTick() {
		return this.bestTime;
	}
	
	public String getFormattedBestTime() {
		return Beapi.formatTimeMMSS(this.bestTime);
	}
	
	public void setComplete(boolean flag) {
		this.isComplete = true;
	}
	
	public boolean isComplete() {
		return this.isComplete;
	}

	public ChallengeCore getCore() {
		return this.core;
	}
	
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.putBoolean("isComplete", this.isComplete);
		nbt.putInt("bestTime", this.bestTime);
		return nbt;
	}

	public void load(CompoundNBT nbt) {
		this.isComplete = nbt.getBoolean("isComplete");
		this.bestTime = nbt.getInt("bestTime");
	}

	@Override
	public boolean equals(Object challenge) {
		if (challenge instanceof ChallengeCore && ((ChallengeCore) challenge).equals(this.getCore())) {
			return true;			
		}

		if (!(challenge instanceof Challenge)) {
			return false;			
		}

		if (this.getCore() == null || ((Challenge) challenge).getCore() == null) {
			return false;			
		}

		if (this.getCore().equals(((Challenge) challenge).getCore())) {
			return true;			
		}

		return false;
	}
}
