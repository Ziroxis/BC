package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ModEffect;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class QualleOperationSpell extends ContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Qualle Operation", AbilityCategories.AbilityCategory.ATTRIBUTE, QualleOperationSpell.class)
            .setDescription("The user heal himself but can't move while doing so")
            .build();
    BlockPos blockPosPlayer;

    public QualleOperationSpell() {
        super(INSTANCE);
        this.onStartContinuityEvent = this::onStartContinuity;
        this.duringContinuityEvent = this::duringSpell;
        this.onEndContinuityEvent = this::onEndContinuityEvent;

    }

    private boolean onStartContinuity(PlayerEntity player) {
        player.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 60, 0));
        return true;
    }

    private void duringSpell(PlayerEntity player, int timer)
    {
        if (timer < 61)
        {
            if (timer % 20 == 0) {
                player.heal(4.0F);
            }
        }
        else
        {
            this.startCooldown(player);
            this.onEndContinuityEvent(player);
        }
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        if (player.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
            player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        return true;
    }
}
