package com.yuanno.block_clover.api.ability;

import com.yuanno.block_clover.init.ModAbilities;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum AbilityCommandGroup implements IExtensibleEnum
{
	GRAVITY(() -> ModAbilities.GRAVITY),
	WIND(() -> ModAbilities.WIND),
	TIME(() -> ModAbilities.TIME),
	WATER(() -> ModAbilities.WATER),
	LIGHTNING(() -> ModAbilities.LIGHTNING),
	SLASH(() -> ModAbilities.SLASH),
	DARKNESS(() -> ModAbilities.DARKNESS),
	LIGHT(() -> ModAbilities.LIGHT),
	FIRE(() -> ModAbilities.FIRE),
	EARTH(() -> ModAbilities.EARTH),
	SEALING(() -> ModAbilities.SEALING),
	ANTIMAGIC(() -> ModAbilities.ANTIMAGIC),
	SWORD(() -> ModAbilities.SWORD),
	MERCURY(() -> ModAbilities.MERCURY),
	BEAST(() -> ModAbilities.BEAST),
	COPY(() -> ModAbilities.COPY),
	MISC(() -> ModAbilities.MISC);


	private Supplier<AbilityCore[]> abilities;

	private AbilityCommandGroup(Supplier<AbilityCore[]> abilities)
	{
		this.abilities = abilities;
	}

	public List<AbilityCore> getAbilities()
	{
		return Arrays.asList(this.abilities.get());
	}

	public static AbilityCommandGroup create(String name, Supplier<AbilityCore[]> abilities)
	{
		throw new IllegalStateException("Enum not extended");
	}
}
