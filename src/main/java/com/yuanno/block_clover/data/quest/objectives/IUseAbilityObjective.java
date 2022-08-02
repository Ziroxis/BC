package com.yuanno.block_clover.data.quest.objectives;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public interface IUseAbilityObjective
{
	boolean checkAbility(PlayerEntity player, Ability ability);
}
