package com.yuanno.block_clover.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ModEffect extends Effect implements IIgnoreMilkEffect {
	public ModEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeModifierManager pAttributeMap, int pAmplifier) {
		super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
		
		if (this.shouldUpdateClient()) {
			Beapi.sendApplyEffectToAllNearby(pLivingEntity, pLivingEntity.position(), 100, pLivingEntity.getEffect(this));
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeModifierManager pAttributeMap, int pAmplifier) {
		super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
		
		if (this.shouldUpdateClient()) {
			Beapi.sendRemoveEffectToAllNearby(pLivingEntity, pLivingEntity.position(), 100, this);
		}
	}
	
	public boolean shouldUpdateClient() {
		return false;
	}
	
	/** Blocks the entity's yaw/pitch rotations */
	public boolean isBlockingRotations() {
		return false;
	}


	@Override
	public boolean isRemoveable() {
		return false;
	}
}
