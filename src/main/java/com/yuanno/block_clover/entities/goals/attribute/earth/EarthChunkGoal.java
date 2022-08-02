package com.yuanno.block_clover.entities.goals.attribute.earth;

import com.yuanno.block_clover.entities.BCentity;
import com.yuanno.block_clover.entities.goals.CooldownGoal;
import com.yuanno.block_clover.entities.projectiles.earth.EarthChunkProjectile;

public class EarthChunkGoal extends CooldownGoal {
    private BCentity entity;

    public EarthChunkGoal(BCentity entity)
    {
        super(entity, 90, entity.getRandom().nextInt(10));
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
        double d1 = this.entity.getTarget().getX() - this.entity.getX();
        double d2 = this.entity.getTarget().getBoundingBox().minY + this.entity.getTarget().getBbHeight() / 2.0F - (this.entity.getY() + this.entity.getBbHeight() / 2.0F);
        double d3 = this.entity.getTarget().getZ() - this.entity.getZ();

        EarthChunkProjectile projectile = new EarthChunkProjectile(this.entity.level, this.entity);
        projectile.setPos(projectile.getX(), this.entity.getY() + this.entity.getBbHeight() / 2.0F + 0.5D, projectile.getZ());
        projectile.shoot(d1 + this.entity.getRandom().nextGaussian(), d2, d3 + this.entity.getRandom().nextGaussian(), 1F, 0);
        this.entity.level.addFreshEntity(projectile);

        //this.entity.setCurrentGoal(this);
        this.setOnCooldown(true);
    }
}
