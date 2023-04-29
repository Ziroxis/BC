package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class MagicCurseEffect extends Effect {
    private LivingEntity entity;
    public MagicCurseEffect()
    {
        super(EffectType.BENEFICIAL, 3381504);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (!entity.level.isClientSide)
        {
            EffectInstance instance = entity.getEffect(this);
            if (entity instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) entity;
                if(instance.getDuration() <= 1)
                {
                    entity.removeEffect(this);
                    if(entity instanceof PlayerEntity)
                    {
                        AbilityHelper.enableAbilities(player, (ability) -> ability != null && ability.getCategory() == AbilityCategories.AbilityCategory.ATTRIBUTE);
                    }
                }
                else
                    AbilityHelper.disableAbilities(player, instance.getDuration(), (ability) -> ability != null && ability.getCategory() == AbilityCategories.AbilityCategory.ATTRIBUTE);
            }
            else
            {
                if (instance.getDuration() <= 1)
                    entity.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());
                else
                    entity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), instance.getDuration()));
            }

        }
    }
}
