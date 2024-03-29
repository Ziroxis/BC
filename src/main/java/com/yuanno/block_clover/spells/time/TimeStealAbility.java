package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TimeStealAbility extends PunchAbility {

    public static final TimeStealAbility INSTANCE = new TimeStealAbility();

    public TimeStealAbility()
    {
        super("Time steal", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Touches an entity, stealing it's time.");
        this.setmanaCost(10);
        this.setMaxCooldown(3);
        this.setExperiencePoint(3);
        this.setExperienceGainLevelCap(10);
        this.onHitEntityEvent = this::onHitEvent;
    }

    private float onHitEvent(PlayerEntity player, LivingEntity target)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getMana() > 15)
        {
            stats.alterMana(-15);
            stats.alterExperience(15);
            if (stats.getTime() < 1000)
            {
                stats.alterTime(10);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            }
            if (target instanceof PlayerEntity)
            {
                PlayerEntity targetPlayer = (PlayerEntity) target;
                if (targetPlayer.getFoodData().getFoodLevel() > 0)
                    targetPlayer.getFoodData().setFoodLevel(targetPlayer.getFoodData().getFoodLevel() - 3);
                else
                    targetPlayer.kill();
            }
            else
              target.kill();
            return 1;
        }
        else
            return 0;

    }
}
