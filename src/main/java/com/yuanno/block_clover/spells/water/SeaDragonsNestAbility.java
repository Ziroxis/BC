package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class SeaDragonsNestAbility extends ContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Sea Dragon's Nest", AbilityCategories.AbilityCategory.ATTRIBUTE, SeaDragonsNestAbility.class)
            .setDescription("The user creates a sphere protecting you from projectiles and spells, moving dispells the spell")
            .build();
    List<BlockPos> blockPos = new ArrayList<>();
    BlockPos blockPosPlayer;
    public SeaDragonsNestAbility() {
        super(INSTANCE);
        this.onStartContinuityEvent = this::onStartContinuity;
        this.duringContinuityEvent = this::duringSpell;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuity(PlayerEntity player) {
        blockPosPlayer = player.blockPosition();
        List<BlockPos> test = Beapi.createSphere(player.level, player.blockPosition().above(), 3, true, ModBlocks.WATER.get(), 2, null);
        blockPos.addAll(test);
        return true;
    }

    private void duringSpell(PlayerEntity player, int timer) {

        double radius = 3.0D; // Define the radius

        // Get all projectiles in a bounding box around the player
        List<ProjectileEntity> projectiles = player.level.getEntitiesOfClass(ProjectileEntity.class, player.getBoundingBox().inflate(radius));

        // Iterate over the projectiles
        for (ProjectileEntity projectile : projectiles) {
            // If the projectile is within the radius, remove it
            if (player.distanceToSqr(projectile) <= radius * radius) {
                projectile.remove();
            }
        }

        if (!blockPosPlayer.equals(player.blockPosition())) {
            this.startContinuity(player);
            this.onEndContinuityEvent(player);
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
