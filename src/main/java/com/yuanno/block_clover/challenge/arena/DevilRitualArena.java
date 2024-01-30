package com.yuanno.block_clover.challenge.arena;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.challenges.ArenaStyle;
import com.yuanno.block_clover.api.challenges.ChallengeArena;
import com.yuanno.block_clover.api.challenges.InProgressChallenge;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.init.ModEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DevilRitualArena extends ChallengeArena {

	public static final DevilRitualArena INSTANCE = new DevilRitualArena();

	private static final int ARENA_SIZE = 25;
	private static final int SAND_HEIGHT = ARENA_SIZE / 3;
	public DevilRitualArena() {
		super(ArenaStyle.BOX);
	}

	@Override
	public void buildArena(InProgressChallenge challenge) {
		final int bottomLayerOffset = (-ARENA_SIZE) + 1;

		// Outer barrier
		ArrayList<BlockPos> blocks1 = new ArrayList<>(Beapi.createEmptyCube(challenge.getShard(),
				challenge.getArenaPos().getX(), challenge.getArenaPos().getY(), challenge.getArenaPos().getZ(), ARENA_SIZE, ARENA_SIZE, ARENA_SIZE, Blocks.BARRIER, null));
		for (BlockPos block : blocks1) {
			challenge.getShard().setBlockAndUpdate(block, Blocks.BARRIER.defaultBlockState());
		}

		ArrayList<BlockPos> blocks2 = new ArrayList<>(Beapi.createPlatform(challenge.getShard(),
				challenge.getArenaPos().getX(), challenge.getArenaPos().getY() - 24, challenge.getArenaPos().getZ(), ARENA_SIZE, ARENA_SIZE, ARENA_SIZE, null));
		for (BlockPos block : blocks2)
		{
			challenge.getShard().setBlockAndUpdate(block, Blocks.OBSIDIAN.defaultBlockState());
		}
		ArrayList<BlockPos> blocks3 = new ArrayList<>(Beapi.createRandomizedPlatform(challenge.getShard(),
				challenge.getArenaPos().getX(), challenge.getArenaPos().getY() - 23, challenge.getArenaPos().getZ(), ARENA_SIZE, ARENA_SIZE, ARENA_SIZE, null));
		for (BlockPos block : blocks3)
		{
			challenge.getShard().setBlockAndUpdate(block, Blocks.TORCH.defaultBlockState());
		}

	}

	@Override
	public void spawnPlayers(InProgressChallenge challenge) {
		for (LivingEntity groupMember : challenge.getGroup()) {
			if (groupMember instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) groupMember;
				player.teleportTo(challenge.getShard(), challenge.getArenaPos().getX() + 15, challenge.getArenaPos().getY() - 23, challenge.getArenaPos().getZ() + 15, 135, 0);
			}
		}
	}


	@Override
	public Set<LivingEntity> spawnEnemies(InProgressChallenge challenge) {

		Set<LivingEntity> set = new HashSet<>();

		WalgnerDevilEntity boss = new WalgnerDevilEntity(ModEntities.WALGNER_DEVIL.get(), challenge.getShard());

		boss.forcedLoading = true;
		challenge.getShard().addFreshEntity(boss);
		boss.teleportTo(challenge.getArenaPos().getX() - 15, challenge.getArenaPos().getY() - 23, challenge.getArenaPos().getZ() - 15);
		boss.setYBodyRot(-45);
		boss.setTarget(challenge.getOwner());
		//GoalUtil.lookAtEntity(boss, challenge.getOwner());
		set.add(boss);

		return set;
	}
}
