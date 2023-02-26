package com.yuanno.block_clover.damagesource;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityDamageSource extends ModEntityDamageSource
{
	private AbilityCore ability;
	private AbilityDamageKind damageKind;


	private boolean isProjectileSource = false;
	private LivingEntity thrower;

	public AbilityDamageSource(String damageType, Entity source, AbilityCore ability)
	{
		super(damageType, source);
		this.ability = ability;
		this.damageKind = ability.getDamageKind();
	}

	public AbilityDamageSource(String damageType, AbilityProjectileEntity source, AbilityCore ability)
	{
		super(damageType, source);
		this.ability = ability;
		this.damageKind = ability.getDamageKind();

		this.isProjectileSource = true;
		this.thrower = source.getThrower();
	}

	public AbilityCore getAbilitySource()
	{
		return this.ability;
	}

	@Override
	public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn)
	{
		String s = "death.attack." + this.msgId;
		return new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), this.ability.getName());
	}
}
