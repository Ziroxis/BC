package com.yuanno.block_clover.data.quest.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IKillEntityObjective
{
    boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source);

}
