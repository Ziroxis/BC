package com.yuanno.block_clover.particles.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TimeResurrectionParticleEffect extends ParticleEffect {
    private static final int NUM_PARTICLES = 200;
    private static final double PARTICLE_SPREAD = 3.0;
    private static final float SIZE = 0.5f;

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double offsetX = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetY = (Math.random() - 0.5) * PARTICLE_SPREAD;
            double offsetZ = (Math.random() - 0.5) * PARTICLE_SPREAD;

            GenericParticleData particle = new GenericParticleData(ModParticleTypes.TIME.get());
            particle.setLife(40);
            particle.setSize(SIZE);
            particle.setMotion(offsetX, offsetY, offsetZ);

            Beapi.spawnParticles(particle, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
        }
    }

}
