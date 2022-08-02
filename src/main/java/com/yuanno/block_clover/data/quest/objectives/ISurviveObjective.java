package com.yuanno.block_clover.data.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;

public interface ISurviveObjective
{
	boolean checkTime(PlayerEntity player);
}
