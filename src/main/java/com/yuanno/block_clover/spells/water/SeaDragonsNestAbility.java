package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class SeaDragonsNestAbility extends ContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Sea Dragon's Nest", AbilityCategories.AbilityCategory.ATTRIBUTE, SeaDragonsNestAbility.class)
            .setDescription("The user creates a sphere protecting everything in")
            .build();
    List<BlockPos> blockPos = new ArrayList<>();
    BlockPos blockPosPlayer;
    public SeaDragonsNestAbility() {
        super(INSTANCE);
        this.duringContinuityEvent = this::duringSpell;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private void duringSpell(PlayerEntity player, int timer) {

    // Create the air bubble around the player
    Beapi.createSphere(player.level, player.blockPosition().above(), 3, false, Blocks.AIR, 2, null);
    List<BlockPos> test = Beapi.createSphere(player.level, player.blockPosition().above(), 3, true, ModBlocks.WATER.get(), 2, null);
    blockPos.addAll(test);

    if (blockPosPlayer == null) {
        blockPosPlayer = player.blockPosition();
    } else if (!blockPosPlayer.equals(player.blockPosition())) {
        for (BlockPos pos : blockPos) {
            BlockState blockState = player.level.getBlockState(pos);
            if (blockState.getBlock().equals(ModBlocks.WATER.get())) {
                player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
        }
        blockPos.clear();
        blockPosPlayer = player.blockPosition();
    }
}

    private boolean onEndContinuityEvent(PlayerEntity player)
    {

        for (BlockPos pos : blockPos) {
            player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
        blockPos.clear();

        return true;
    }
}
