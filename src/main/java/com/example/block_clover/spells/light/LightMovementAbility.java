package com.example.block_clover.spells.light;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;

public class LightMovementAbility extends Ability {
    public static final LightMovementAbility INSTANCE = new LightMovementAbility();

    public LightMovementAbility()
    {
        super("Light Movement", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setmanaCost(30);
        this.setMaxCooldown(3);
        this.setExperiencePoint(10);
        this.onUseEvent = this::onUseEvent;
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        BlockPos pos = Beapi.rayTraceBlockSafe(player, 8 + stats.getLevel());
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), player.yRot,
                    player.xRot, Collections.emptySet());
        }
        return true;
    }
}
