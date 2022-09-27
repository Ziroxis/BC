package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;

public class TimeRessurectionAbility extends PassiveAbility {

    public static final TimeRessurectionAbility INSTANCE = new TimeRessurectionAbility();
    public TimeRessurectionAbility()
    {
        super("Time ressurection", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Consumes all your time, giving yourself another chance.");
        this.hideInGUI(false);
        this.setMaxCooldown(180);
        this.duringPassiveEvent = this::duringPassive;
    }

    public void duringPassive(PlayerEntity player)
    {
        float health = player.getHealth();
        if (health < 2)
        {
            IEntityStats entityStats = EntityStatsCapability.get(player);
            entityStats.setTime(0);
            this.startCooldown(player);
            player.heal(player.getMaxHealth() - player.getHealth());
        }
    }
}
