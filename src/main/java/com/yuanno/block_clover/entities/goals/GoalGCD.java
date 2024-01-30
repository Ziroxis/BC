package com.yuanno.block_clover.entities.goals;

public class GoalGCD
{
	private static final int MAX_GCD = 40;
	private boolean onGCD;
	private int gcd = MAX_GCD;
	
	public void tick()
	{
		if(this.onGCD)
		{
			if(this.gcd < MAX_GCD)
			{
				this.gcd++;
			}
			else
			{
				this.gcd = 0;
				this.onGCD = false;
			}
		}
	}
	
	public int getCurrentGCD()
	{
		return this.gcd;
	}
	
	public void startGCD()
	{
		this.onGCD = true;
	}
	
	public boolean isOnGCD()
	{
		return this.onGCD;
	}
}
