package com.yuanno.block_clover.entities.goals.attribute.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.entities.BCentity;
import com.yuanno.block_clover.entities.beastial.MonkeyEntity;
import com.yuanno.block_clover.entities.goals.CooldownGoal;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.spells.wind.ToweringTornadoAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class WindTornadoGoal extends CooldownGoal {
    private BCentity entity;

    public WindTornadoGoal(BCentity entity)
    {
        super(entity, 110, entity.getRandom().nextInt(10));
        this.entity = entity;
        //this.entity.addThreat(3);
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
        //this.entity.setCurrentGoal(null);
        //this.entity.setPreviousGoal(this);
    }

    @Override
    public void start()
    {
        List<LivingEntity> entities = Beapi.getEntitiesAround(this.entity.blockPosition(), this.entity.level, 12F, LivingEntity.class);
        entities.remove(this.entity);

        entities.forEach(entityi ->
        {
            if (!(entityi instanceof MonkeyEntity))
            {
                Vector3d speed = Beapi.Propulsion(this.entity, 5, 5, 5);
                entityi.setDeltaMovement(speed.x, speed.y, speed.z);
                entityi.hurtMarked = true;
                entityi.hasImpulse = true;
                entityi.hurt(ModDamageSource.causeAbilityDamage(this.entity, ToweringTornadoAbility.INSTANCE), 7);
            }
        });

        if (this.entity.level instanceof ServerWorld)
        {
            ((ServerWorld) this.entity.level).sendParticles(ParticleTypes.SPIT, this.entity.getX(), this.entity.getY(), this.entity.getZ(), (int) 100, 3, 2, 3, 1);
        }

        //this.entity.setCurrentGoal(this);
        this.setOnCooldown(true);
    }
}
