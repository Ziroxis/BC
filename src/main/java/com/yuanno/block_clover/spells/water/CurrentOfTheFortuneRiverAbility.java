package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CurrentOfTheFortuneRiverAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Current Of The Fortune River", AbilityCategories.AbilityCategory.ATTRIBUTE, CurrentOfTheFortuneRiverAbility.class)
            .setDescription("Allows you to walk on water")
            .build();

    public CurrentOfTheFortuneRiverAbility()
    {
        super(INSTANCE);
        this.setmanaCost(3);
        this.setExperiencePoint(2);
        this.setExperienceGainLevelCap(20);
        this.duringPassiveEvent = this::duringContinuity;
    }

    public void duringContinuity(PlayerEntity player)
    {
        World world = player.level;
        BlockPos blockPos = player.blockPosition();
        if (world.getBlockState(blockPos.below()).getBlock() == Blocks.WATER && player.getY() - blockPos.below().getY() < 1.1) {
            player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
            player.fallDistance = 0;
            player.setOnGround(true);
        }
    }
}
