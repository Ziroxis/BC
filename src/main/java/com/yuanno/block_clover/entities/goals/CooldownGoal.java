package com.yuanno.block_clover.entities.goals;

import com.yuanno.block_clover.entities.api.BCentity;

public abstract class CooldownGoal<T extends BCentity> extends AbilityGoal<T>
{
	private boolean isOnCooldown = false;
	protected double maxCooldown, cooldown = 80;
	protected int randomizer = 1;
	private int ticksInUse;

	public CooldownGoal(T entity)
	{
		super(entity);
	}

	@Override
	public boolean canUse()
	{
		if(!super.canUse()) {
			return false;
		}

		if (this.isOnCooldown()) {
			return this.cooldownTick();
		}

		if(this.entity.getGoalGCD().isOnGCD()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean canContinueToUse()
	{
		return false;
	}

	@Override
	public void tick()
	{
		this.ticksInUse++;
	}

	@Override
	public void stop()
	{
		this.ticksInUse = 0;
		this.setOnCooldown(true);
	}

	public <G extends CooldownGoal> G setMaxCooldown(double cooldown)
	{
		this.cooldown = cooldown * 20;
		this.maxCooldown = this.cooldown;
		this.randomizer = (int) Math.max(1, (this.maxCooldown / 10));
		return (G) this;
	}

	public int getTicksInUse()
	{
		return this.ticksInUse;
	}

	/** Get maximum cooldown in ticks */
	public double getMaxCooldown()
	{
		return this.maxCooldown;
	}

	public void setOnCooldown(boolean value)
	{
		this.isOnCooldown = value;
		if(value) {
			this.entity.getGoalGCD().startGCD();
		}
	}

	public boolean isOnCooldown()
	{
		return this.isOnCooldown;
	}

	public boolean cooldownTick()
	{
		if (this.isOnCooldown)
		{
			this.cooldown--;
			this.duringCooldown();
			if (this.cooldown <= 0)
			{
				this.endCooldown();
				this.isOnCooldown = false;
				this.cooldown = this.maxCooldown + this.entity.getRandom().nextInt(this.randomizer);
				return true;
			}

			return false;
		}

		return false;
	}

	public void duringCooldown() {};

	public void endCooldown() {};
}
