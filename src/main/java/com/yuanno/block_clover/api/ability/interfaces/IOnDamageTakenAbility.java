package com.yuanno.block_clover.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public interface IOnDamageTakenAbility {
    public boolean isDamageTaken(LivingEntity entity, DamageSource source, double amount);

}
