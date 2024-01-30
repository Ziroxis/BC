package com.yuanno.block_clover.data.challenges;

import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;

import java.util.List;

public interface IChallengesData {
	boolean addChallenge(Challenge challenge);
	boolean addChallenge(ChallengeCore<?> core);
	boolean removeChallenge(ChallengeCore<?> core);
	boolean hasChallenge(ChallengeCore<?> core);
	boolean isChallengeCompleted(ChallengeCore<?> core);
	<T extends Challenge> T getChallenge(ChallengeCore<?> core);
	List<Challenge> getChallenges();
	void clearChallenges(); 
	int countChallenges();
}
