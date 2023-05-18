package com.yuanno.block_clover.particles.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightningOrbParticleEffect extends ParticleEffect {

    private static final int NUM_PARTICLES = 20;
    private static final double PARTICLE_SPREAD = 0.5;
    private static final double OFFSET = 1.5;

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double offsetX = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetY = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetZ = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double particlePosX = posX - OFFSET * motionX;
            double particlePosY = posY - OFFSET * motionY;
            double particlePosZ = posZ - OFFSET * motionZ;
            GenericParticleData windParticle = new GenericParticleData(ModParticleTypes.LIGHTNING.get());
            windParticle.setLife(10);
            windParticle.setSize(0.5f);
            windParticle.setMotion(0, 0.01, 0);
            Beapi.spawnParticles(windParticle, (ServerWorld) world, particlePosX + offsetX, particlePosY + offsetY, particlePosZ + offsetZ);
        }
    }
}
