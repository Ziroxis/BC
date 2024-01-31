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

public class ChallengeReward {
	public static final ChallengeReward EMPTY = new ChallengeReward();


	private List<Supplier<ItemStack>> items = new ArrayList<Supplier<ItemStack>>();
	private List<Supplier<ChallengeCore>> challenges = new ArrayList<>();
	private List<Supplier<Ability>> abilities = new ArrayList<>();
	private List<Supplier<String>> devils = new ArrayList<>();
	private List<Supplier<ItemStack>> itemToRemove = new ArrayList<>();
	public List<Supplier<ItemStack>> getItems() {
		return this.items;
	}

	public ChallengeReward addAbility(Supplier<Ability> ability)
	{
		abilities.add(ability);
		return this;
	}

	public ChallengeReward addDevil(Supplier<String> devil)
	{
		devils.add(devil);
		return this;
	}

	public ChallengeReward addChallenge(Supplier<ChallengeCore> challengeSupplier)
	{
		this.challenges.add(challengeSupplier);
		return this;
	}

	public ChallengeReward addItem(Supplier<ItemStack> item) {
		this.items.add(item);
		return this;
	}

	public ChallengeReward addItemToRemove(Supplier<ItemStack> item)
	{
		this.itemToRemove.add(item);
		return this;
	}

	public ChallengeReward addItemsToRemove(ArrayList<Supplier<ItemStack>> itemsToRemove)
	{
		this.itemToRemove.addAll(itemsToRemove);
		return this;
	}


	public String giveRewards(PlayerEntity player) {
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
			sb.append(" " + ability.getName() + " " + "unlocked" + "\n");
			IAbilityData abilityData = AbilityDataCapability.get(player);
			abilityData.addUnlockedAbility(player, ability);
		}

		for (Supplier<String> supplier : this.devils)
		{
			String devilname = supplier.get();
			sb.append(" " + devilname + " " + "has been subjugated" + "\n");
			IDevil devil = DevilCapability.get(player);
			devil.addControlledDevilList(devilname);
		}

		for (Supplier<ItemStack> itemStack : this.itemToRemove)
		{
			if (player.inventory.contains(itemStack.get())) {
				player.inventory.removeItem(itemStack.get());
				break;
			}
		}



		if(hasAtLeastOneReward) {
			sb.append("\n");
		}

		return sb.toString();
	}

}
