package com.yuanno.block_clover.particles.sword;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.text.Color;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OriginalSlashParticleEffect extends ParticleEffect {
    private static final int NUM_PARTICLES = 20;
    private static final double PARTICLE_SPREAD = 0.5;
    private static final double OFFSET = 1.0;
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

            GenericParticleData slashParticle = new GenericParticleData(ModParticleTypes.SLASH.get());
            slashParticle.setColor(1, 0, 0, 0);
            slashParticle.setLife(10);
            slashParticle.setSize(SIZE);
            slashParticle.setMotion(offsetX, offsetY, offsetZ);

            Beapi.spawnParticles(slashParticle, (ServerWorld) world, particlePosX + offsetX, particlePosY + offsetY, particlePosZ + offsetZ);
        }
    }




}
