package com.yuanno.block_clover.entities.goals;

import com.yuanno.block_clover.entities.api.BCentity;
import net.minecraft.entity.ai.goal.Goal;

public abstract class AbilityGoal<T extends BCentity> extends Goal
{
	protected T entity;
	
	public AbilityGoal(T entity)
	{
		this.entity = entity;
	}
	
	@Override
	public boolean canUse()
	{
		if(this.isChargingAbility())
			return false;
		
		if(this.isOtherAbilityRunning())
			return false;

		return true;
	}
	
	public boolean isOtherAbilityRunning()
	{
		return this.entity.goalSelector.getRunningGoals().anyMatch(g -> g.getGoal() instanceof AbilityGoal && !(g.getGoal() instanceof IParallelGoal));	
	}
	
	public boolean isChargingAbility()
	{
		return this.entity.goalSelector.getRunningGoals().anyMatch(g -> g.getGoal() instanceof ChargingGoal && !(g.getGoal() instanceof IParallelGoal) && ((ChargingGoal)g.getGoal()).isCharging());
	}
}
