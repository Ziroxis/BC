package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
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
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private void duringSpell(PlayerEntity player, int timer) {
        int playerX = (int) player.getX();
        int playerY = (int) player.getY();
        int playerZ = (int) player.getZ();


    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        int playerX = (int) player.getX();
        int playerY = (int) player.getY();
        int playerZ = (int) player.getZ();


        for (int x = playerX - 2; x <= playerX + 4; x++) {
            for (int y = playerY - 2; y <= playerY + 4; y++) {
                for (int z = playerZ - 1; z <= playerZ + 4; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState blockState = player.level.getBlockState(pos);
                    if (blockState.getBlock() == Blocks.WATER) {
                        player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        return true;
    }
}
