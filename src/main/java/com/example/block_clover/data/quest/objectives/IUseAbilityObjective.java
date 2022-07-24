package com.example.block_clover.data.quest.objectives;

import com.example.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public interface IUseAbilityObjective
{
	boolean checkAbility(PlayerEntity player, Ability ability);
}
