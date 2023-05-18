package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightMovementParticleEffect;
import com.yuanno.block_clover.particles.time.TimeHopParticleEffect;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;

public class TimeHopAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Time hop", AbilityCategories.AbilityCategory.ATTRIBUTE, TimeHopAbility.class)
            .setDescription("Hops in time teleporting somewhere else.\nCrouch for a further distance.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    int distance;
    private static final ParticleEffect PARTICLES = new TimeHopParticleEffect();

    public TimeHopAbility()
    {
        super(INSTANCE);
        this.setDescription("Hops in time teleporting somewhere else.\nCrouch for a further distance");
        this.setmanaCost(20);
        this.setMaxCooldown(2);
        this.onUseEvent = this::onUseEvent;
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getTime() > 20)
        {
            stats.alterTime(-20);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            if (player.isCrouching())
                distance = 20;
            else
                distance = 8;
            BlockPos pos = Beapi.rayTraceBlockSafe(player, distance);
            player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            if (player instanceof ServerPlayerEntity)
            {
                PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
                ((ServerPlayerEntity) player).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), player.yRot,
                        player.xRot, Collections.emptySet());
                PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

            }
            return true;
        }
        return false;
    }
}
