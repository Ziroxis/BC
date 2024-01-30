package com.yuanno.block_clover.api;

public class Interval {
	protected int interval;
	protected int tick = 0;
	
	/**
	 * @param interval - amount of ticks it takes before the interval can tick
	 */
	public Interval(int interval) {
		this.interval = interval;
	}
	
	public static Interval startAtZero(int interval) {
		return new Interval(interval);
	}
	
	public static Interval startAtMax(int interval) {
		Interval intervalObj = new Interval(interval);
		intervalObj.tick = interval;
		return intervalObj;
	}

	public boolean canTick() {
		if (--this.tick <= 0) {
			this.tick = this.interval;
			return true;
		}
		
		return false;
	}

	public int getTick() {
		return this.tick;
	}
	
	public static class Mutable extends Interval {

		public Mutable(int interval) {
			super(interval);
		}

		public void setInterval(int interval) {
			this.interval = interval;
			this.tick = interval;
		}
	}
}
