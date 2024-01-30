package com.yuanno.block_clover.particles;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CommonExplosionParticleEffect extends ParticleEffect
{
	private int explosionSize;

	public CommonExplosionParticleEffect()
	{
		this(2);
	}

	public CommonExplosionParticleEffect(int explosionSize)
	{
		this.explosionSize = explosionSize;
	}

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < this.explosionSize * 2; i++)
		{
			double x = posX + BeJavapi.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + Beapi.randomDouble();
			double y = posY + Beapi.randomDouble();
			double z = posZ + BeJavapi.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + Beapi.randomDouble();

			motionX = BeJavapi.randomWithRange(-2, 2) + Beapi.randomDouble();
			motionY = BeJavapi.randomWithRange(-2, 2) + Beapi.randomDouble();
			motionZ = BeJavapi.randomWithRange(-2, 2) + Beapi.randomDouble();

			double middlePoint = 0.5D / (5 / this.explosionSize + 0.1D);
			middlePoint *= (Beapi.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			Beapi.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld) world, x, y + 1, z);
			Beapi.spawnParticles(ParticleTypes.POOF, (ServerWorld) world, posX, posY + 1, posZ);
		}
	}

}