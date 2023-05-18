package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class PoisonousCurseEffect extends Effect {
    private LivingEntity entity;

    public PoisonousCurseEffect()
    {
        super(EffectType.HARMFUL, 3381504);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
        if(!entity.level.isClientSide()) {
            this.entity = entity;
            entity.hurt(ModDamageSource.DEVILS_CURSE, 4);
        }
        super.applyEffectTick(entity, p_76394_2_);
    }
    @Override
    public boolean isDurationEffectTick(int timer, int amplifier)
    {
        if (timer % 40 == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
