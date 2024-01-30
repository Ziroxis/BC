package com.yuanno.block_clover.api.challenges;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ChallengeInfo {

	private static final ImmutableList<LivingEntity> EMPTY_GROUP = ImmutableList.of();
	
	@Nullable
	private InProgressChallenge challengeData;
	
	public ChallengeDifficulty getDifficulty() {
		if (this.challengeData == null) {
			return ChallengeDifficulty.STANDARD;
		}
		return this.challengeData.getCore().getDifficulty();
	}
	
	public List<LivingEntity> getChallengerGroup() {
		if (this.challengeData == null) {
			return EMPTY_GROUP;
		}
		return this.challengeData.getGroup();
	}
	
	public boolean isDifficultyStandard() {
		return this.getDifficulty() == ChallengeDifficulty.STANDARD;
	}
	
	public boolean isDifficultyHard() {
		return this.getDifficulty() == ChallengeDifficulty.HARD;
	}
	
	public boolean isDifficultyUltimate() {
		return this.getDifficulty() == ChallengeDifficulty.ULTIMATE;
	}
	
	public float getGroupScaling() {
		if (this.getChallengerGroup().isEmpty() || this.getChallengerGroup().size() == 1) {
			return 1.0f;
		}
		float scale = (this.getChallengerGroup().size() / 2) * (this.getDifficulty().ordinal() * 1.25f);
		scale = MathHelper.clamp(scale, 1, 5);
		return scale;
	}

	public void setInProgressChallenge(@Nullable InProgressChallenge data) {
		this.challengeData = data;
	}
	
	@Nullable
	public InProgressChallenge getInProgressChallengeData() {
		return this.challengeData;
	}
}
