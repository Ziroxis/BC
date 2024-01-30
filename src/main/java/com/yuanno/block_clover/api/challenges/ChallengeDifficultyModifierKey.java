package com.yuanno.block_clover.api.challenges;

import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class ChallengeDifficultyModifierKey {

	private IModifierMethod method;

	public ChallengeDifficultyModifierKey(IModifierMethod method) {
		this.method = method;
	}

	@Nullable
	public IModifierMethod getMethod() {
		return this.method;
	}
	
	public void tryApply(LivingEntity entity, Number multiplier) {
		if (this.method != null) {
			this.method.apply(entity, multiplier);
		}
	}

	public static interface IModifierMethod {
		void apply(LivingEntity entity, Number multiplier);
	}
}
