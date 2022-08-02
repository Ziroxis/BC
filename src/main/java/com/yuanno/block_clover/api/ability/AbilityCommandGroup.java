package com.yuanno.block_clover.api.ability;

import com.yuanno.block_clover.init.ModAbilities;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum AbilityCommandGroup implements IExtensibleEnum
{
	WIND(() -> ModAbilities.WIND),
	LIGHTNING(() -> ModAbilities.LIGHTNING),
	SLASH(() -> ModAbilities.SLASH),
	DARKNESS(() -> ModAbilities.DARKNESS),
	LIGHT(() -> ModAbilities.LIGHT),
	FIRE(() -> ModAbilities.FIRE),
	EARTH(() -> ModAbilities.EARTH),
	ANTIMAGIC(() -> ModAbilities.ANTIMAGIC);

	
	private Supplier<Ability[]> abilities;
	
	private AbilityCommandGroup(Supplier<Ability[]> abilities)
	{
		this.abilities = abilities;
	}
	
	public List<Ability> getAbilities()
	{
		return Arrays.asList(this.abilities.get());
	}
	
	public static AbilityCommandGroup create(String name, Supplier<Ability[]> abilities)
	{
		throw new IllegalStateException("Enum not extended");
	}
}
