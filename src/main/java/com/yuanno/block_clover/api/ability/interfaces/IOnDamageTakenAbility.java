package com.yuanno.block_clover.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

/**
 * Interface for abilities that trigger when the entity takes damage
 */
public interface IOnDamageTakenAbility {
    public boolean isDamageTaken(LivingEntity entity, DamageSource source, double amount);

}
