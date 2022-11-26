package com.yuanno.block_clover.api.Quest.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface IEntityInteractObjective
{
	boolean checkInteraction(PlayerEntity player, Entity entity);
}
