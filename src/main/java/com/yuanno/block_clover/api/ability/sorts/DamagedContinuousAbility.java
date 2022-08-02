package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

import java.io.Serializable;

public class DamagedContinuousAbility extends ContinuousAbility {
	
    protected IOnDamaged onDamagedEvent = (player, attacker, amount) -> false;

    public DamagedContinuousAbility(String name, AbilityCategories.AbilityCategory category)
    {
        super(name, category);
    }

    public boolean damage(LivingEntity entity, DamageSource source, double amount)
    {
        return this.onDamagedEvent.onDamage(entity, source, amount);
    }

    public interface IOnDamaged extends Serializable
    {
        boolean onDamage(LivingEntity entity, DamageSource source, double amount);
    }
}
