package com.yuanno.block_clover.api.challenges;

import com.google.common.collect.HashMultimap;
import com.yuanno.block_clover.init.ModRegistries;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ChallengeCore<T extends Challenge> extends ForgeRegistryEntry<ChallengeCore<?>> {
	@Nullable
	private IFormattableTextComponent title;
	private final String unlocalizedTitle;
	private IFormattableTextComponent objective;
	private final String unlocalizedObjective;
	private final String category;
	private int timeLimit; // In minutes!
	private ChallengeReward reward;
	private ChallengeReward secondReward;
	private ChallengeOffering challengeOffering;
	private ChallengeDifficulty difficulty;
	private int difficultyStars;
	private HashMultimap<ArenaStyle, ChallengeArena> arenas = HashMultimap.create();
	private Supplier<EntityType<?>>[] targetShowcase;
	private String[] bannedFactions;
	private final IFactory<T> factory;
	private ICanStart startCheck;
	@Nullable
	private String titleI18nId;
	@Nullable
	private String objectiveI18nId;
	private int order;
	
	public ChallengeCore(String unlocalizedTitle, String unlocalizedObjective, String category, IFactory<T> factory) {
		this.unlocalizedTitle = unlocalizedTitle;
		this.unlocalizedObjective = unlocalizedObjective;
		this.category = category;
		this.factory = factory;
	}

	@Nullable
	public T createChallenge() {
		return this.factory.create(this);
	}

	private void setTargetShowcase(Supplier<EntityType<?>>... targets) {
		this.targetShowcase = targets;
	}
	
	private void setArenas(HashMultimap<ArenaStyle, ChallengeArena> map) {
		this.arenas = map;
	}
	
	private void setDifficulty(ChallengeDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	private void setDifficultyStars(int stars) {
		this.difficultyStars = stars;
	}
	
	private void setTimeLimit(int minutes) {
		this.timeLimit = minutes;
	}

	private void setReward(ChallengeReward reward) {
		this.reward = reward;
	}
	private void setSecondReward(ChallengeReward reward) {
		this.secondReward = reward;
	}
	private void setChallengeOffering(ChallengeOffering challengeOffering)
	{
		this.challengeOffering = challengeOffering;
	}

	public void setBannedFactions(String... factions) {
		this.bannedFactions = factions;
	}

	public void setStartCheck(ICanStart startCheck) {
		this.startCheck = startCheck;
	}
	
	public IFormattableTextComponent getLocalizedTitle() {
		if (this.title == null) {
			this.title = new TranslationTextComponent(this.getUnlocalizedTitle());
		}
		return this.title;
	}
	
	public IFormattableTextComponent getLocalizedObjective() {
		if (this.objective == null) {
			this.objective = new TranslationTextComponent(this.getUnlocalizedObjective());
		}
		return this.objective;
	}

	private String getTitleLocalizationId() {
		if (this.titleI18nId == null) {
			ResourceLocation key = ModRegistries.CHALLENGES.getKey(this);
			if (key != null) {
				this.titleI18nId = Util.makeDescriptionId("challenge", key);
			}
		}
		return this.titleI18nId;
	}

	private String getObjectiveLocalizationId() {
		if (this.titleI18nId == null) {
			ResourceLocation key = ModRegistries.CHALLENGES.getKey(this);
			if (key != null) {
				this.objectiveI18nId = Util.makeDescriptionId("challenge", key) + ".objective";
			}
		}
		return this.objectiveI18nId;
	}

	public EntityType<?>[] getTargetShowcase() {
		EntityType<?>[] types = new EntityType<?>[this.targetShowcase.length];
		int i = 0;
		for(Supplier<EntityType<?>> type : this.targetShowcase) {
			types[i] = type.get();
			i++;
		}
		return types;
	}
	
	public ChallengeArena pickRandomArena() {
		ArenaStyle configStyle = ArenaStyle.BOX;
		for(ChallengeArena arena : this.arenas.get(configStyle)) {
			return arena;
		}
		return null;
	}

	private void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public ChallengeDifficulty getDifficulty() {
		return this.difficulty;
	}
	
	public int getDifficultyStars() {
		return this.difficultyStars;
	}
	
	public String getUnlocalizedTitle() {
		return this.unlocalizedTitle;
	}
	
	public String getUnlocalizedObjective() {
		return this.unlocalizedObjective;
	}
	
	public IFactory<? extends T> getFactory() {
		return this.factory;
	}

	public ITextComponent getObjective() {
		return this.objective;
	}

	public String getCategory() {
		return this.category;
	}

	public int getTimeLimit() {
		return this.timeLimit;
	}

	public ChallengeReward getReward() {
		return this.reward;
	}

	public ChallengeReward getSecondReward()
	{
		return this.secondReward;
	}

	public ChallengeOffering getChallengeOffering()
	{
		return this.challengeOffering;
	}

	public String[] getBannedFactions() {
		return this.bannedFactions;
	}
	
	public ICanStart getStartCheck() {
		return this.startCheck;
	}

	public static class Builder<T extends Challenge> {
		private String unlocalizedTitle;
		private String unlocalizedObjective;
		private String category = "No Category";
		private int timeLimit = 10; // In minutes!
		private ChallengeReward reward = ChallengeReward.EMPTY;
		private ChallengeReward secondReward = ChallengeReward.EMPTY;
		private ChallengeOffering challengeOffering = ChallengeOffering.EMPTY;
		private ChallengeDifficulty difficulty = ChallengeDifficulty.STANDARD;
		private int difficultyStars;
		private HashMultimap<ArenaStyle, ChallengeArena> arenas = HashMultimap.create();
		private Supplier<EntityType<?>>[] targetShowcase;
		private String[] bannedFactions;
		private IFactory<T> factory;
		private ICanStart startCheck = (entity) -> true;
		private int order = Integer.MAX_VALUE;

		public Builder(String title, String objective, String category, IFactory<T> factory) {
			this.unlocalizedTitle = title;
			this.unlocalizedObjective = objective;
			this.category = category;
			this.factory = factory;
		}

		public Builder<T> setOrder(int order) {
			this.order = order;
			return this;
		}

		public Builder<T> setTimeLimit(int minutes) {
			this.timeLimit = minutes;
			return this;
		}

		public Builder<T> setReward(ChallengeReward reward) {
			this.reward = reward;
			return this;
		}

		public Builder<T> setSecondReward(ChallengeReward reward) {
			this.secondReward = reward;
			return this;
		}

		public Builder<T> setOffering(ChallengeOffering challengeOffering)
		{
			this.challengeOffering = challengeOffering;
			return this;
		}

		public Builder<T> setBannedFactions(String... factions) {
			this.bannedFactions = factions;
			return this;
		}

		public Builder<T> setStartCheck(ICanStart startCheck) {
			this.startCheck = startCheck;
			return this;
		}
		
		public Builder<T> setDifficulty(ChallengeDifficulty difficulty) {
			this.difficulty = difficulty;
			return this;
		}
		
		public Builder<T> setDifficultyStars(int stars) {
			this.difficultyStars = Math.min(stars, ModValues.MAX_DIFFICULTY_STARS);
			return this;
		}
		
		public Builder<T> addArena(ArenaStyle style, ChallengeArena arena) {
			this.arenas.put(style, arena);
			return this;
		}
		
		public Builder<T> setTargetShowcase(Supplier<EntityType<?>>... targets) {
			this.targetShowcase = targets;
			return this;
		}
		
		public ChallengeCore build() {
			ChallengeCore challenge = new ChallengeCore(this.unlocalizedTitle, this.unlocalizedObjective, this.category, this.factory);
			challenge.setTimeLimit(this.timeLimit);
			challenge.setReward(this.reward);
			challenge.setSecondReward(this.secondReward);
			challenge.setChallengeOffering(this.challengeOffering);
			challenge.setBannedFactions(this.bannedFactions);
			challenge.setStartCheck(this.startCheck);
			challenge.setDifficulty(this.difficulty);
			challenge.setDifficultyStars(this.difficultyStars);
			challenge.setArenas(this.arenas);
			challenge.setTargetShowcase(this.targetShowcase);

			if (this.difficulty == ChallengeDifficulty.HARD) {
				this.order += 1;
			}
			else if (this.difficulty == ChallengeDifficulty.ULTIMATE) {
				this.order += 2;
			}

			challenge.setOrder(this.order);
			return challenge;
		}
	}

	@FunctionalInterface
	public interface IFactory<A extends Challenge> {
		A create(ChallengeCore<A> ability);
	}

	@FunctionalInterface
	public interface ICanStart {
		boolean canStart(PlayerEntity player);
	}
}
