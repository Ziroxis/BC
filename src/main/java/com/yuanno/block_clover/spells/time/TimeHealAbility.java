package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.light.LightHealingParticleEffect;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;

public class TimeHealAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Time healing", AbilityCategories.AbilityCategory.ATTRIBUTE, TimeHealAbility.class)
            .setDescription("Consumes time, healing yourself.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private static final ParticleEffect PARTICLES = new LightHealingParticleEffect();

    public TimeHealAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getTime() > 30)
        {
            entityStats.alterTime(-30);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            player.heal(10);
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
            return true;
        }
        return false;
    }
}
