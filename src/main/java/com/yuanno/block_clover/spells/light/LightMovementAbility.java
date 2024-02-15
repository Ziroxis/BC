package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightMovementParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;

public class LightMovementAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Light movement", AbilityCategories.AbilityCategory.ATTRIBUTE, LightMovementAbility.class)
            .setDescription("Moves as fast as light")
            .setDamageKind(AbilityDamageKind.MOVEMENT)
            .build();
    private static final ParticleEffect PARTICLES = new LightMovementParticleEffect();

    public LightMovementAbility()
    {
        super(INSTANCE);
        this.setmanaCost(45);
        this.setMaxCooldown(4);
        this.setExperiencePoint(10);
        this.setEvolutionCost(100);

        this.onUseEvent = this::onUseEvent;
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        BlockPos pos = Beapi.rayTraceBlockSafe(player, 8 + stats.getLevel());
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        if (player instanceof ServerPlayerEntity) {
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
            ((ServerPlayerEntity) player).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), player.yRot,
                    player.xRot, Collections.emptySet());
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

        }
        return true;
    }
}
