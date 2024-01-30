package com.yuanno.block_clover.api.challenges;

import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChallengeReward {
	public static final ChallengeReward EMPTY = new ChallengeReward();


	private int kan = 0;
	private List<Supplier<ItemStack>> items = new ArrayList<Supplier<ItemStack>>();
	private List<Supplier<ChallengeCore>> challenges = new ArrayList<>();


	public List<Supplier<ItemStack>> getItems() {
		return this.items;
	}

	public int getKan()
	{
		return this.kan;
	}

	public ChallengeReward setKan(int amount)
	{
		this.kan = amount;
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



		if(hasAtLeastOneReward) {
			sb.append("\n");
		}

		return sb.toString();
	}

}
