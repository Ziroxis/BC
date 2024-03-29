package com.yuanno.block_clover.events.projectiles;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ProjectileShootEvent extends Event
{
	private AbilityProjectileEntity projectile;
	private float velocity;
	private float inaccuracy;



	public ProjectileShootEvent(AbilityProjectileEntity abilityProjectileEntity, float velocity, float inaccuracy) {
		this.projectile = abilityProjectileEntity;
		this.velocity = velocity;
		this.inaccuracy = inaccuracy;

	}

	public AbilityProjectileEntity getProjectile()
	{
		return this.projectile;
	}

	public float getVelocity()
	{
		return this.velocity;
	}
	
	public float getInaccuracy()
	{
		return this.inaccuracy;
	}
}
