package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IOnDamageTakenAbility;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class TimeMagicManaZoneAbility extends PassiveAbility implements IOnDamageTakenAbility {

    public static final TimeMagicManaZoneAbility INSTANCE = new TimeMagicManaZoneAbility();

    IEntityStats entityStats;
    private BlockPos centerBlock;

    boolean zoneSet = false;

    public TimeMagicManaZoneAbility()
    {
        super("Time magic zone", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a zone around you accelerated in time, sensing future incoming attacks");
        //this.setmanaCost(0);
        this.hideInGUI(false);
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
        }
        return result;
    }
}
