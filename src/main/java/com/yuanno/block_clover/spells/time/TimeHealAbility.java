package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;

public class TimeHealAbility extends Ability {

    public static final Ability INSTANCE = new TimeHealAbility();

    public TimeHealAbility()
    {
        super("Time healing", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Consumes time, healing yourself");
        this.setMaxCooldown(5);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getTime() > 15)
        {
            entityStats.alterTime(-15);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            player.heal(5);
            return true;
        }
        return false;
    }
}
