package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.io.Serializable;

public class PunchAbility extends ContinuousAbility{
    private boolean isStoppingAfterHit = true;

    protected IOnHitEntity onHitEntityEvent = (player, target) -> { return 0; };
    protected IOnHitEffect onHitEffectEvent = (player, target) -> {};

    public PunchAbility(AbilityCore core)
    {
        super(core);
    }

    public void hitEffect(PlayerEntity player, LivingEntity target)
    {
        this.onHitEffectEvent.hitEffect(player, target);

        if(this.isStoppingAfterHit)
            this.endContinuity(player);
    }
    public float hitEntity(PlayerEntity player, LivingEntity target)
    {
        float result = this.onHitEntityEvent.onHitEntity(player, target);


        this.endContinuity(player);
        return result;
    }
    public DamageSource getPunchDamageSource(PlayerEntity player)
    {
        return ModDamageSource.causeAbilityDamage(player, this);
    }
    public interface IOnHitEntity extends Serializable
    {
        float onHitEntity(PlayerEntity player, LivingEntity target);
    }

    public interface IOnHitEffect extends Serializable
    {
        void hitEffect(PlayerEntity player, LivingEntity target);
    }
}
