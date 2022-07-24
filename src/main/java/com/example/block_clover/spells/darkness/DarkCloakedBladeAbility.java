package com.example.block_clover.spells.darkness;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousSwordAbility;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class DarkCloakedBladeAbility extends ContinuousSwordAbility implements IParallelContinuousAbility {

    public static final DarkCloakedBladeAbility INSTANCE = new DarkCloakedBladeAbility();
    private boolean cancelled = false;

    public DarkCloakedBladeAbility()
    {
        super("Dark Cloaked Blade", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Envelops your blade with darkness");
        this.setMaxCooldown(10);
        this.setmanaCost(5);
        this.setExperiencePoint(3);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onHitEntityEvent = this::onHitEntity;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            return false;
        }
        return true;
    }

    private boolean duringContinuityEvent(PlayerEntity player, int i)
    {
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            this.stopContinuity(player);
            return false;
        }
        return true;
    }

    private float onHitEntity(PlayerEntity player, LivingEntity target)
    {
        // SAFE CHECK
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            return 0;
        }



        return 4;
    }
}
