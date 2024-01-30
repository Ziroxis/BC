package com.yuanno.block_clover.entities.goals.spells;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.entities.BCentity;
import com.yuanno.block_clover.entities.goals.CooldownGoal;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public class CrowSpellGoal extends CooldownGoal {
    private BCentity entity;

    public CrowSpellGoal(BCentity entity)
    {
        super(entity);
        this.setMaxCooldown(3);
        this.entity = entity;
    }

    @Override
    public boolean canUse()
    {
        boolean shouldExecute = super.canUse();
        boolean hasTarget = this.entity.getTarget() != null;
        boolean hasDistance = hasTarget && this.entity.distanceTo(this.entity.getTarget()) > 4;
        boolean hasEnemyInSight = hasTarget && this.entity.canSee(this.entity.getTarget());

        if (shouldExecute && hasTarget && hasEnemyInSight && hasDistance)
            return true;

        return false;
    }

    @Override
    public void endCooldown()
    {
        super.endCooldown();
    }

    @Override
    public void start()
    {
        List<LivingEntity> entities = Beapi.getEntitiesAround(this.entity.blockPosition(), this.entity.level, 16F, LivingEntity.class);
        entities.remove(this.entity);
        entities.forEach(entity ->
        {
            entity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 100, 0));
            entity.hurt(ModDamageSource.causeAbilityDamage(this.entity, CrowAbility.INSTANCE), 10);
        });
        this.setOnCooldown(true);
    }
}
