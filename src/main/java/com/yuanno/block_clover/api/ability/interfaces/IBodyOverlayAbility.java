package com.yuanno.block_clover.api.ability.interfaces;

import com.yuanno.block_clover.api.ability.AbilityOverlay;
import net.minecraft.entity.LivingEntity;

public interface IBodyOverlayAbility
{
	AbilityOverlay getBodyOverlay(LivingEntity entity);
}
