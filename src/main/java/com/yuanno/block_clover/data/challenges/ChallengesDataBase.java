package com.yuanno.block_clover.data.challenges;



import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengesDataBase implements IChallengesData {
	private List<Challenge> challenges = new ArrayList<Challenge>();

	@Override
	public boolean addChallenge(Challenge challenge) {
		if (this.hasChallenge(challenge.getCore())) {
			return false;
		}

		this.challenges.add(challenge);
		return true;
	}

	@Override
	public boolean addChallenge(ChallengeCore<?> core) {
		if (this.hasChallenge(core)) {
			return false;
		}

		Challenge challenge = core.createChallenge();
		this.challenges.add(challenge);
		return true;
	}

	@Override
	public boolean removeChallenge(ChallengeCore<?> core) {
		if (!this.hasChallenge(core)) {
			return false;			
		}

		this.challenges.removeIf(ch -> ch.getCore().equals(core));
		return true;
	}

	@Override
	public boolean hasChallenge(ChallengeCore<?> core) {
		return this.getChallenge(core) != null;
	}
	
	@Override
	public boolean isChallengeCompleted(ChallengeCore<?> core) {
		Challenge ch = this.getChallenge(core);
		if (ch == null) {
			return false;
		}
		return ch.isComplete();
	}

	@Nullable
	@Override
	public <T extends Challenge> T getChallenge(ChallengeCore<?> core) {
		return (T) this.challenges.stream().filter(ch -> ch.getCore().equals(core)).findFirst().orElse(null);
	}

	@Override
	public List<Challenge> getChallenges() {
		return this.challenges.stream().filter(ch -> ch != null).collect(Collectors.toList());
	}

	@Override
	public int countChallenges() {
		this.challenges.removeIf(chl -> chl == null);
		return this.challenges.size();
	}

	@Override
	public void clearChallenges() {
		this.challenges.clear();
	}
}
