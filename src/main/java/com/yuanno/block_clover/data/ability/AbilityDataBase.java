package com.yuanno.block_clover.data.ability;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.init.ModAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbilityDataBase implements IAbilityData
{
	private List<Ability> unlockedAbilities = new ArrayList<Ability>();
	private Ability[] equippedAbilities = new Ability[128];

	private Ability previouslyUsedAbility;
	private int currentCombatBarSet = 0;
	private boolean isEvolved = false;
	private int experience = 0;
	/*
	 * Unlocked Abilities
	 */

	@Override
	public boolean isEvolved() {
		return this.isEvolved;
	}

	@Override
	public void evolve(boolean evolve) {
		this.isEvolved = evolve;
	}

	@Override
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public void alterExperience(int experience) {
		this.experience = this.experience + experience;
	}

	@Override
	public int getExperience() {
		return this.experience;
	}

	@Override
	public boolean addUnlockedAbility(PlayerEntity player, Ability abl)
	{
		if(abl == null || this.hasUnlockedAbility(abl))
			return false;

		this.unlockedAbilities.add(abl);

		if (player instanceof ServerPlayerEntity)
			ModAdvancements.UNLOCK_ABILITY.trigger((ServerPlayerEntity)player, abl.getCore());

		return true;
	}

	@Override
	public boolean addUnlockedAbility(PlayerEntity player, AbilityCore core)
	{
		Ability abl = core.createAbility();
		return this.addUnlockedAbility(player, abl);
	}

	@Override
	public boolean loadUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl == null)
		{
			this.unlockedAbilities.add(abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean setUnlockedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl == null)
		{
			if(this.unlockedAbilities.size() > slot)
				this.unlockedAbilities.set(slot, abl);
			else
				this.unlockedAbilities.add(slot, abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl != null)
		{
			this.unlockedAbilities.remove(ogAbl);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUnlockedAbility(AbilityCore core)
	{
		Ability abl = this.getUnlockedAbility(core);
		if (abl != null)
		{
			this.unlockedAbilities.remove(abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasUnlockedAbility(Ability abl)
	{
		return this.getUnlockedAbilities().stream().anyMatch(ability -> ability.equals(abl));
	}

	@Override
	public boolean hasUnlockedAbility(AbilityCore core)
	{
		return this.getUnlockedAbilities().stream().anyMatch(ability -> ability.getCore().equals(core));
	}

	@Override
	public <T extends Ability> T getUnlockedAbility(T abl)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return (T) this.unlockedAbilities.stream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
	}

	@Override
	public <T extends Ability> T getUnlockedAbility(AbilityCore core)
	{
		return (T) this.getUnlockedAbilities().stream().filter(ability -> ability.getCore().equals(core)).findFirst().orElse(null);
	}

	@Override
	public <T extends Ability> T getUnlockedAbility(int slot)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.size() > slot ? (T) this.unlockedAbilities.get(slot) : null;
	}

	@Override
	public <T extends Ability> List<T> getUnlockedAbilities()
	{
		return (List<T>) this.unlockedAbilities.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	@Override
	public <T extends Ability> List<T> getUnlockedAbilities(Predicate<Ability> check)
	{
		return (List<T>) this.getUnlockedAbilities().stream()
				.filter(check)
				.collect(Collectors.toList());
	}

	@Override
	public <T extends Ability> List<T> getUnlockedAbilities(AbilityCategories.AbilityCategory category)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return (List<T>) this.unlockedAbilities.stream().filter(ability -> ability.getCategory() == category || category == AbilityCategories.AbilityCategory.ALL).collect(Collectors.toList());
	}

	@Override
	public <T extends Ability> List<T> clearUnlockedAbilities(AbilityCategories.AbilityCategory category)
	{
		return this.clearUnlockedAbilities(ability -> ability.getCategory() == category || category == AbilityCategories.AbilityCategory.ALL);
	}

	@Override
	public <T extends Ability> List<T> clearUnlockedAbilities(Predicate<T> check)
	{
		List<T> removed = (List<T>) this.unlockedAbilities.stream()
				.filter(ability -> ability == null || check.test((T) ability))
				.collect(Collectors.toList());

		this.unlockedAbilities.removeAll(removed);

		return removed;
	}

	@Override
	public void clearUnlockedAbilityFromList(AbilityCategories.AbilityCategory category, List<Ability> list)
	{
		this.unlockedAbilities.removeIf(ability -> (ability == null || ability.getCategory() == category || category == AbilityCategories.AbilityCategory.ALL) && list.contains(ability));
	}

	@Override
	public int countUnlockedAbilities(AbilityCategories.AbilityCategory category)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities
				.stream()
				.filter(ability -> !(ability instanceof PassiveAbility))
				.filter(ability -> ability.getCategory() == category || category == AbilityCategories.AbilityCategory.ALL)
				.collect(Collectors.toList()).size();
	}

	/*
	 * Equipped Abilities
	 */

	@Override
	public boolean addEquippedAbility(Ability abl)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if(ability == null)
			{
				this.equippedAbilities[i] = abl;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setEquippedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getEquippedAbility(abl);
		if (ogAbl == null)
		{
			this.equippedAbilities[slot] = abl;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEquippedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl != null)
		{
			for(int i = 0; i < this.equippedAbilities.length; i++)
			{
				Ability ability = this.equippedAbilities[i];
				if(ability != null)
				{
					this.equippedAbilities[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeEquippedAbility(AbilityCore abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl != null)
		{
			for(int i = 0; i < this.equippedAbilities.length; i++)
			{
				Ability ability = this.equippedAbilities[i];
				if(ability != null)
				{
					this.equippedAbilities[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasEquippedAbility(Ability abl)
	{
		return Arrays.stream(this.equippedAbilities)
				.filter(ability -> ability != null)
				.anyMatch(ability -> ability.equals(abl));
	}

	@Override
	public boolean hasEquippedAbility(AbilityCore core)
	{
		return Arrays.stream(this.equippedAbilities)
				.filter(ability -> ability != null)
				.anyMatch(ability -> ability.getCore().equals(core));
	}

	@Override
	public int getEquippedAbilitySlot(Ability abl)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if(ability != null && ability.equals(abl))
				return i;
		}

		return -1;
	}

	@Nullable
	@Override
	public <T extends Ability> T getEquippedAbility(T abl)
	{
		return (T) Arrays.stream(this.equippedAbilities)
				.filter(ability -> ability != null && ability.equals(abl))
				.findFirst().orElse(null);
	}

	@Nullable
	@Override
	public <T extends Ability> T getEquippedAbility(AbilityCore<T> core)
	{
		return (T) Arrays.stream(this.equippedAbilities)
				.filter(ability -> ability != null && ability.getCore().equals(core))
				.findFirst().orElse(null);
	}

	@Nullable
	@Override
	public <T extends Ability> T getEquippedAbility(int slot)
	{
		return (T) this.equippedAbilities[slot];
	}

	@Override
	public<T extends Ability> T[] getEquippedAbilities()
	{
		Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
		List<Ability> list1 = stream.collect(Collectors.toList());
		Ability list2[] = new Ability[list1.size()];
		return (T[]) list1.toArray(list2);
	}

	@Override
	public <T extends Ability> T[] getEquippedAbilities(Predicate<Ability> check)
	{
		Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
		stream = stream.filter(check);
		List<Ability> list1 = stream.collect(Collectors.toList());
		Ability list2[] = new Ability[list1.size()];
		return (T[]) list1.toArray(list2);
	}

	@Override
	public <T extends Ability> T[] getEquippedAbilities(AbilityCategories.AbilityCategory category)
	{
		List<Ability> list1 = Arrays.stream(this.equippedAbilities).filter(ability -> (ability != null && ability.getCategory() == category) || category == AbilityCategories.AbilityCategory.ALL).collect(Collectors.toList());
		Ability list2[] = new Ability[list1.size()];
		return (T[]) list1.toArray(list2);
	}

	@Override
	public void clearEquippedAbilities(Predicate<Ability> predicate)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if(ability != null && predicate.test(ability))
			{
				this.equippedAbilities[i] = null;
			}
		}
	}

	@Override
	public void clearEquippedAbilities(AbilityCategories.AbilityCategory category)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if((ability != null && ability.getCategory() == category) || category == AbilityCategories.AbilityCategory.ALL)
			{
				this.equippedAbilities[i] = null;
			}
		}
	}

	@Override
	public void clearEquippedAbilityFromList(AbilityCategories.AbilityCategory category, List<Ability> list)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if((ability != null && ability.getCategory() == category && list.contains(ability)) || category != AbilityCategories.AbilityCategory.ALL)
			{
				this.equippedAbilities[i] = null;
			}
		}
	}

	@Override
	public int countEquippedAbilities(AbilityCategories.AbilityCategory category)
	{
		return Arrays.stream(this.equippedAbilities)
				.filter(ability -> ability != null && (ability.getCategory() == category || category == AbilityCategories.AbilityCategory.ALL) && !(ability instanceof PassiveAbility))
				.collect(Collectors.toList())
				.size();
	}

	/*
	 * Previously Used Ability
	 */

	@Override
	public <T extends Ability> T getPreviouslyUsedAbility()
	{
		return (T) this.previouslyUsedAbility;
	}

	@Override
	public void setPreviouslyUsedAbility(Ability abl)
	{
		this.previouslyUsedAbility = abl;
	}


	@Override
	public int getCombatBarSet()
	{
		return this.currentCombatBarSet;
	}

	@Override
	public void nextCombatBarSet()
	{
		this.currentCombatBarSet++;
	}

	@Override
	public void prevCombatBarSet()
	{
		this.currentCombatBarSet--;
	}

	@Override
	public void setCombatBarSet(int set)
	{
		this.currentCombatBarSet = set;
	}
}
