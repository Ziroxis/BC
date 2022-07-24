package com.example.block_clover.api.ability.interfaces;

import com.example.block_clover.api.ability.AbilityOverlay;
import net.minecraft.entity.LivingEntity;

public interface IPunchOverlayAbility
{
	AbilityOverlay getPunchOverlay(LivingEntity player);
}
