package com.yuanno.block_clover.data.ability;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IAbilityData
{
	boolean isEvolved();

	void evolve(boolean evolve);

	void setExperience(int experience);

	void alterExperience(int experience);

	int getExperience();

	boolean addUnlockedAbility(PlayerEntity player, Ability abl);
	boolean addUnlockedAbility(PlayerEntity player, AbilityCore core);
	boolean loadUnlockedAbility(Ability abl);
	@Deprecated
	boolean setUnlockedAbility(int slot, Ability abl);
	boolean removeUnlockedAbility(Ability abl);
	boolean removeUnlockedAbility(AbilityCore core);
	boolean hasUnlockedAbility(Ability abl);
	boolean hasUnlockedAbility(AbilityCore core);
	<T extends Ability> T getUnlockedAbility(T abl);
	<T extends Ability> T getUnlockedAbility(AbilityCore core);
	<T extends Ability> T getUnlockedAbility(int slot);
	<T extends Ability> List<T> getUnlockedAbilities();
	<T extends Ability> List<T> getUnlockedAbilities(Predicate<Ability> check);
	<T extends Ability> List<T> getUnlockedAbilities(AbilityCategories.AbilityCategory category);
	<T extends Ability> List<T> clearUnlockedAbilities(AbilityCategories.AbilityCategory category);
	<T extends Ability> List<T> clearUnlockedAbilities(Predicate<T> check);
	void clearUnlockedAbilityFromList(AbilityCategories.AbilityCategory category, List<Ability> list);
	int countUnlockedAbilities(AbilityCategories.AbilityCategory category);

	boolean addEquippedAbility(Ability abl);
	boolean setEquippedAbility(int slot, Ability abl);
	boolean removeEquippedAbility(Ability abl);
	boolean removeEquippedAbility(AbilityCore abl);
	boolean hasEquippedAbility(Ability abl);
	boolean hasEquippedAbility(AbilityCore abl);
	int getEquippedAbilitySlot(Ability abl);
	<T extends Ability> T getEquippedAbility(T abl);
	<T extends Ability> T getEquippedAbility(AbilityCore<T> core);
	<T extends Ability> T getEquippedAbility(int slot);
	<T extends Ability> List<T> getEquippedAbilities();
	<T extends Ability> List<T> getEquippedAbilities(Predicate<Ability> check);
	<T extends Ability> T[] getEquippedAbilities(AbilityCategories.AbilityCategory category);
	void clearEquippedAbilities(AbilityCategories.AbilityCategory category);
	void clearEquippedAbilities(Predicate<Ability> check);
	void clearEquippedAbilityFromList(AbilityCategories.AbilityCategory category, List<Ability> list);
	int countEquippedAbilities(AbilityCategories.AbilityCategory category);

	<T extends Ability> T getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);

	int getCombatBarSet();
	void nextCombatBarSet();
	void prevCombatBarSet();
	void setCombatBarSet(int set);}
