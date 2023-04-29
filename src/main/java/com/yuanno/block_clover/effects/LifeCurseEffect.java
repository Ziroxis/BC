package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class LifeCurseEffect extends Effect {
    private LivingEntity entity;

    public LifeCurseEffect()
    {
        super(EffectType.HARMFUL, 3381504);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
        if(!entity.level.isClientSide()) {
            this.entity = entity;
            entity.kill();
        }
        super.applyEffectTick(entity, p_76394_2_);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int p_76397_2_) {
        if(duration < 20) {
            return true;
        } else {
            return false;
        }
    }}
