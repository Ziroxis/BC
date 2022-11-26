package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.IUseAbilityObjective;
import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public class UseAbilityObjective extends Objective implements IUseAbilityObjective
{
	protected ICheckAbilityUse useEvent = (player, ability) -> true;
	
	public UseAbilityObjective(String title, int count)
	{
		this(title, count, (ICheckAbilityUse)null);
	}

	public UseAbilityObjective(String title, int count, Ability ability)
	{
		this(title, count, (player, abl) -> abl.equals(ability));
	}
	
	public UseAbilityObjective(String title, int count, ICheckAbilityUse check)
	{
		super(title);
		this.setMaxProgress(count);
		if(check != null)
			this.useEvent = check;
	}

	@Override
	public boolean checkAbility(PlayerEntity player, Ability ability)
	{
		return this.useEvent.test(player, ability);
	}
	
	@FunctionalInterface
	public interface ICheckAbilityUse
	{
		boolean test(PlayerEntity player, Ability ability);
		
		default ICheckAbilityUse and(ICheckAbilityUse check)
		{
			return (player, ability) -> {
				return this.test(player, ability) && check.test(player, ability);		
			};
		}
		
		default ICheckAbilityUse or(ICheckAbilityUse check)
		{
			return (player, ability) -> {
				return this.test(player, ability) || check.test(player, ability);		
			};
		}
	}

}
