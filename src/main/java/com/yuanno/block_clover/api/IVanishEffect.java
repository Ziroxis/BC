package com.yuanno.block_clover.api;

import net.minecraft.entity.LivingEntity;

/**
 * Used for {@link net.minecraft.potion.Effect} to completely disable the body rendering and all renderings associated with it.
 * @author wynd
 */
public interface IVanishEffect {

	public boolean isVanished(LivingEntity entity, int duration, int amplifier);
	
	public boolean disableParticles();
}
