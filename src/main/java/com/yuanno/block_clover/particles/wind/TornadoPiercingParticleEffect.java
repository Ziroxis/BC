package com.yuanno.block_clover.particles.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.rmi.registry.RegistryHandler;

public class TornadoPiercingParticleEffect extends ParticleEffect {

    private static final int NUM_PARTICLES = 50;
    private static final double PARTICLE_SPREAD = 2.0;
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

            GenericParticleData fireworkParticle = new GenericParticleData(ModParticleTypes.WIND.get());
            fireworkParticle.setLife(20);
            fireworkParticle.setSize(SIZE);
            fireworkParticle.setMotion(offsetX, offsetY, offsetZ);

            Beapi.spawnParticles(fireworkParticle, (ServerWorld) world, particlePosX + offsetX, particlePosY + offsetY, particlePosZ + offsetZ);
        }
    }
}
