package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.ModEffect;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class InEventEffect extends ModEffect {

	public InEventEffect() {
		super(EffectType.NEUTRAL, 0);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256, AttributeModifier.Operation.ADDITION)
			.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256, AttributeModifier.Operation.ADDITION)
			.addAttributeModifier(Attributes.ATTACK_SPEED, "777f14bf-fe3f-4f19-9d2c-eb69c04d5209", -4, AttributeModifier.Operation.ADDITION)
			.addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION)
	        .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean shouldUpdateClient() {
		return true;
	}
	
	@Override
	public boolean shouldRender(EffectInstance effect) {
		return false;
	}

	@Override
	public boolean shouldRenderHUD(EffectInstance effect) {
		return false;
	}

	@Override
	public boolean isBlockingRotations() {
		return true;
	}

	
	@Override
	public boolean isRemoveable() {
		return false;
	}
}
