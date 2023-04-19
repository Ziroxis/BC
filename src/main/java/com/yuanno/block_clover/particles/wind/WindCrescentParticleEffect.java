package com.yuanno.block_clover.particles.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WindCrescentParticleEffect extends ParticleEffect {

    private static final int NUM_PARTICLES = 5;
    private static final double PARTICLE_SPREAD = 0.5;
    private static final double OFFSET = 1.5;
    private static final float SIZE = 0.5f;

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double offsetX = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetY = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetZ = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double particlePosX = posX - OFFSET * motionX;
            double particlePosY = posY - OFFSET * motionY;
            double particlePosZ = posZ - OFFSET * motionZ;

            GenericParticleData windParticle = new GenericParticleData(ModParticleTypes.WIND.get());
            windParticle.setLife(20);
            windParticle.setSize(SIZE);
            windParticle.setMotion(0, 0.01, 0);

            double lineOffsetX = (Math.random() - 0.5) * 0.2;
            double lineOffsetZ = (Math.random() - 0.5) * 0.2;
            double linePosX = particlePosX + offsetX + lineOffsetX;
            double linePosZ = particlePosZ + offsetZ + lineOffsetZ;
            double lineLength = Math.random() * 0.5 + 0.2;
            double lineYStep = lineLength / 10;
            for (double y = 0; y < lineLength; y += lineYStep) {
                Beapi.spawnParticles(windParticle, (ServerWorld) world, linePosX, particlePosY + y + offsetY, linePosZ);
            }
        }
    }
}
