package com.yuanno.block_clover.api.challenges;

import net.minecraft.entity.LivingEntity;

import java.util.Set;

public abstract class ChallengeArena {
	
	private ArenaStyle style;
	
	public ChallengeArena(ArenaStyle style) {
		this.style = style;
	}
	
	public abstract void buildArena(InProgressChallenge data);
	
	public abstract void spawnPlayers(InProgressChallenge data);
	
	public abstract Set<LivingEntity> spawnEnemies(InProgressChallenge data);

	public ArenaStyle getStyle() {
		return this.style;
	}
}
