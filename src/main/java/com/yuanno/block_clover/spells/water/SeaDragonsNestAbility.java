package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class SeaDragonsNestAbility extends ContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Sea Dragon's Nest", AbilityCategories.AbilityCategory.ATTRIBUTE, SeaDragonsNestAbility.class)
            .setDescription("The user creates a sphere protecting everything in")
            .build();

    public SeaDragonsNestAbility() {
        super(INSTANCE);
        this.duringContinuityEvent = this::duringSpell;
    }

    private void duringSpell(PlayerEntity player, int timer) {
        int playerX = (int) player.getX();
        int playerY = (int) player.getY();
        int playerZ = (int) player.getZ();

        for (int x = playerX - 3; x <= playerX + 1; x++) {
            for (int y = playerY - 3; y <= playerY + 3; y++) {
                for (int z = playerZ - 3; z <= playerZ + 1; z++) {
                    // Skip the blocks in a 2-block radius around the player
                    if (Math.abs(x - playerX) <= 2 && Math.abs(y - playerY) <= 2 && Math.abs(z - playerZ) <= 2) {
                        continue;
                    }

                    // Get the block at the current position
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = player.level.getBlockState(pos);

                    // If the block is air, replace it with water
                    if (state.isAir()) {
                        player.level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
                    }
                }
            }
        }

        for (int x = playerX - 2; x <= playerX + 0; x++) {
            for (int y = playerY - 2; y <= playerY + 2; y++) {
                for (int z = playerZ - 1; z <= playerZ + 1; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState blockState = player.level.getBlockState(pos);
                    if (blockState.getBlock() == Blocks.WATER) {
                        player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

    }
}
