package com.yuanno.block_clover.api.ability.interfaces;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

import java.util.function.Predicate;

public interface IOnDamageAbility
{
	final static Predicate<Ability> IS_ACTIVE = (ability) -> {
		if(!(ability instanceof IOnDamageAbility))
			return false;
		
		if(ability instanceof ContinuousAbility)
			return ability.isContinuous();
		else if(ability instanceof PassiveAbility)
			return !((PassiveAbility)ability).isPaused();
		
		return false;
	};
	
    public boolean onDamage(LivingEntity entity, DamageSource source, double amount);
}
