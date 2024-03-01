package com.yuanno.block_clover.spells.darkness;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.darkness.DarkCloakedBladeParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class DarkCloakedBladeAbility extends PunchAbility implements IParallelContinuousAbility {

    private static final ParticleEffect PARTICLES = new DarkCloakedBladeParticleEffect();
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Dark cloaked blade", AbilityCategories.AbilityCategory.ATTRIBUTE, DarkCloakedBladeAbility.class)
            .setDescription("Envelops your blade with darkness")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();


    public DarkCloakedBladeAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(5);
        this.setEvolutionCost(50);
        this.setExperiencePoint(7);
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

    private void duringContinuityEvent(PlayerEntity player, int i)
    {
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            this.endContinuity(player);
            return;
        }
        double test = player.yBodyRot;
        double testCoordsX = test;
        double testCoordsY = test;
        double testCoordsZ = test;


        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
    }

    private float onHitEntity(PlayerEntity player, LivingEntity target)
    {
        // SAFE CHECK
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            this.endContinuity(player);
            return 0;
        }
        PARTICLES.spawn(player.level, target.getX(), target.getY(), target.getZ(), 0, 0, 0);

        return 3;
    }
}
