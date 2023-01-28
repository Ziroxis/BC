package com.yuanno.block_clover.api;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class SetOnFireEvent extends LivingEvent
{
	private LivingEntity attacker;
	private int fireTime;
	
	public SetOnFireEvent(LivingEntity attacker, LivingEntity target, int fireTime)
	{
		super(target);
		this.attacker = attacker;
		this.fireTime = fireTime;
	}

	public LivingEntity getAttacker()
	{
		return this.attacker;
	}
	
	public int getFireTime()
	{
		return this.fireTime;
	}
}
