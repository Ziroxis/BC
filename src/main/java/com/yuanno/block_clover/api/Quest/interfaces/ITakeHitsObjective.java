package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface ITakeHitsObjective {


    boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount);
}
