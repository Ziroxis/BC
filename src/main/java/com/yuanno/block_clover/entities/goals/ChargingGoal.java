package com.yuanno.block_clover.entities.goals;


import com.yuanno.block_clover.entities.BCentity;

public abstract class ChargingGoal<T extends BCentity> extends CooldownGoal<T> {
	private boolean isCharging = false;
	private int maxCharge = 1, charge;

	public ChargingGoal(T entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public boolean canUse() {
		if (!super.canUse()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.isOnCooldown() && this.isCharging();
	}

	@Override
	public void start() {
		super.start();
		this.isCharging = true;
	}

	@Override
	public void stop() {
		super.stop();
		this.stopCharging();
	}

	@Override
	public void tick() {
		if (this.isCharging) {
			this.charge++;
			if (this.charge >= this.maxCharge) {
				this.stopCharging();
			}
		}

		super.tick();
	}

	public void setMaxCharge(double seconds) {
		this.maxCharge = (int) (seconds * 20);
	}

	public void stopCharging() {
		this.isCharging = false;
		this.charge = 0;
	}

	public boolean isCharging() {
		return this.isCharging;
	}

	public int getCurrentCharge() {
		return this.charge;
	}

	/** Get maximum charge time in ticks */
	public int getMaxCharge() {
		return this.maxCharge;
	}
}
