package com.yuanno.block_clover.api.challenges;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public interface IChallangeBoss {

	static final UUID GROUP_SCALE_HP_UUID = UUID.fromString("a66a0da1-286f-4772-9684-1b30810152f5");
	static final UUID GROUP_SCALE_TOUGHNESS_UUID = UUID.fromString("64c28e45-7adf-41b5-8d64-933ca6b8288b");
	static final UUID GROUP_SCALE_ATTACK_UUID = UUID.fromString("378cc4c7-d7a2-47f8-8b94-123ef5229196");

	public ChallengeInfo getChallengeInfo();
	
	public default void applyDifficultyModifiers(LivingEntity entity) {
		// Difficulty modifiers for each boss based on the difficulty of their challenge
		if(this.getDifficultyModifiers() != null) {
			for(Map.Entry<ChallengeDifficultyModifierKey, Number> entry : this.getDifficultyModifiers().entrySet()) {
				entry.getKey().tryApply(entity, entry.getValue());
			}			
		}

		// HP, Toughness and Attack modifiers based on how many players join the challenge (the scaling does take difficulty into consideration)
		float scale = this.getChallengeInfo().getGroupScaling();
		if (scale > 1) {
			ModifiableAttributeInstance hpAttr = entity.getAttribute(Attributes.MAX_HEALTH);
			if (hpAttr != null) {
				// Weird minecraft multiplier fuckery...there's probably a 1 added to our value
				// somewhere down the line idk
				hpAttr.addPermanentModifier(new AttributeModifier(GROUP_SCALE_HP_UUID, "Group Scaling Health Modifier", scale - 1, Operation.MULTIPLY_BASE));
			}



			ModifiableAttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
			if (attackAttr != null) {
				attackAttr.addPermanentModifier(new AttributeModifier(GROUP_SCALE_ATTACK_UUID, "Group Scaling Health Modifier", scale, Operation.ADDITION));
			}
		}
	}
	
	@Nullable
	public default Map<ChallengeDifficultyModifierKey, Number> getDifficultyModifiers() {
		return this.getDifficultyModifiers(this.getChallengeInfo().getDifficulty());
	}
	
	// TODO remove default
	@Nullable
	public default Map<ChallengeDifficultyModifierKey, Number> getDifficultyModifiers(ChallengeDifficulty difficulty) {
		return null;
	}
	
	public default double getModifierOrDefault(ChallengeDifficultyModifierKey multiplier) {
		return this.getDifficultyModifiers(this.getChallengeInfo().getDifficulty()).getOrDefault(multiplier, 1).doubleValue();
	}

	/* -> do this //TODO
	@Nullable
	public default NPCPhaseManager<?> getPhaseManager() {
		return null;
	}
	
	@Nullable
	public default RevengeMeter getRevengeMeter() {
		return null;
	}

	 */
	
	@Deprecated
	public default boolean isDifficultyHard() {
		return this.getChallengeInfo().getDifficulty() == ChallengeDifficulty.HARD;
	}
}
