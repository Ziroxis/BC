package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IOnDamageTakenAbility;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.time.TimeHopParticleEffect;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class TimeMagicManaZoneAbility extends PassiveAbility implements IOnDamageTakenAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Time magic zone", AbilityCategories.AbilityCategory.ATTRIBUTE, TimeMagicManaZoneAbility.class)
            .setDescription("Creates a zone around you accelerated in time, sensing future incoming attacks.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    IEntityStats entityStats;
    private BlockPos centerBlock;
    private static final ParticleEffect PARTICLES = new TimeHopParticleEffect();

    boolean zoneSet = false;

    public TimeMagicManaZoneAbility()
    {
        super(INSTANCE);
        //this.setmanaCost(0);
        //this.hideInGUI(false);
        this.setMaxCooldown(180);

    }

    @Override
    public boolean isDamageTaken(LivingEntity livingEntity, DamageSource damageSource, double amount)
    {
        boolean result;
        if (this.isPaused())
            return true;
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            entityStats = EntityStatsCapability.get(player);
        }
        result = entityStats.getMana() <= 0 || entityStats.getTime() <= 0;
        entityStats.alterTime(-5);
        entityStats.alterMana(-5);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(livingEntity.getId(), entityStats), (PlayerEntity) livingEntity);

        if (!result)
        {
            livingEntity.teleportTo(livingEntity.getX() + Beapi.RNG(2) + 1, livingEntity.getY(), livingEntity.getZ() + Beapi.RNG(2) + 1);
            if (!livingEntity.level.isClientSide)
                PARTICLES.spawn(livingEntity.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0, 0, 0);

        }
        return result;
    }
}
