package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class HealingCurseEffect extends Effect {

    private LivingEntity entity;

    public HealingCurseEffect()
    {
        super(EffectType.BENEFICIAL, 3381504);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
        if(!entity.level.isClientSide()) {
            this.entity = entity;
            if (entity.getHealth() < entity.getMaxHealth())
                entity.heal(2);
            else if (entity.getHealth() > entity.getMaxHealth())
                entity.setHealth(entity.getMaxHealth());


        }
        super.applyEffectTick(entity, p_76394_2_);
    }

    @Override
    public boolean isDurationEffectTick(int timer, int amplifier)
    {
        if (timer % 40 == 0 )
        {
            return true;
        }
        else {
            return false;
        }
    }
}
