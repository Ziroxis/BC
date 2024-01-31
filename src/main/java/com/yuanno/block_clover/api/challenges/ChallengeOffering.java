package com.yuanno.block_clover.api.challenges;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChallengeOffering {
	public static final ChallengeOffering EMPTY = new ChallengeOffering();


	private List<Supplier<ItemStack>> items = new ArrayList<Supplier<ItemStack>>();
	private List<Supplier<ChallengeCore>> challenges = new ArrayList<>();
	private List<Supplier<Ability>> abilities = new ArrayList<>();
	private List<Supplier<String>> devils = new ArrayList<>();
	private List<Supplier<ItemStack>> itemToRemove = new ArrayList<>();
	public List<Supplier<ItemStack>> getItems() {
		return this.items;
	}

	public ChallengeOffering addAbility(Supplier<Ability> ability)
	{
		abilities.add(ability);
		return this;
	}

	public ChallengeOffering addDevil(Supplier<String> devil)
	{
		devils.add(devil);
		return this;
	}

	public ChallengeOffering addChallenge(Supplier<ChallengeCore> challengeSupplier)
	{
		this.challenges.add(challengeSupplier);
		return this;
	}

	public ChallengeOffering addItem(Supplier<ItemStack> item) {
		this.items.add(item);
		return this;
	}

	public ChallengeOffering addItemToRemove(Supplier<ItemStack> item)
	{
		this.itemToRemove.add(item);
		return this;
	}

	public ChallengeOffering addItemsToRemove(ArrayList<Supplier<ItemStack>> itemsToRemove)
	{
		this.itemToRemove.addAll(itemsToRemove);
		return this;
	}


	public String takeOffering(PlayerEntity player) {
		IChallengesData challengesData = ChallengesDataCapability.get(player);
		StringBuilder sb = new StringBuilder();

		boolean hasAtLeastOneReward = false;

		for (Supplier<ItemStack> supp : this.items) {
			ItemStack stack = supp.get().copy();
			// Keep the rewards array above the addItem method, down the line it will set
			// the count of our stack to 0 resulting in "0 air" to be displayed
			// instead...thanks Mojang
			// Since we only need the name, cloning the stack would be a waste of resources,
			// so we keep it above, put the name in list and move on
			sb.append("  " + stack.getDisplayName().getString() + (stack.getCount() > 1 ? " - " + stack.getCount() : "") + "\n");
			player.addItem(stack);
		}

		for (Supplier<Ability> supplier : this.abilities)
		{
			Ability ability = supplier.get();
			sb.append(" " + ability.getName() + " " + "removed" + "\n");
			IAbilityData abilityData = AbilityDataCapability.get(player);
			abilityData.addUnlockedAbility(player, ability);
		}

		for (Supplier<String> supplier : this.devils)
		{
			String devilname = supplier.get();
			sb.append(" " + devilname + " " + "has been released" + "\n");
			IDevil devil = DevilCapability.get(player);
			devil.addControlledDevilList(devilname);
		}

		checkItems(player, this.itemToRemove, sb);





		if(hasAtLeastOneReward) {
			sb.append("\n");
		}

		return sb.toString();
	}
	public void checkItems(PlayerEntity player, List<Supplier<ItemStack>> items, StringBuilder sb)
	{
		for (Supplier<ItemStack> itemStack : items)
		{
			for (int i = 0; i < player.inventory.getContainerSize(); i++) {
				if (player.inventory.getItem(i).getItem().equals(itemStack.get().getItem())) {
					player.inventory.getItem(i).shrink(1);
					sb.append("Took: " + itemStack.get().getDisplayName().getString() + " as offering");
					return;
				}
			}
		}
	}
}
