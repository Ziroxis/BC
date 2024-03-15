package com.yuanno.block_clover.particles.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WhirlwindParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 20; i++)
        {
            double radius = 1.0; // Adjust this radius to control the distance from the center
            double angle = world.getRandom().nextDouble() * Math.PI * 2; // Random angle in radians

            // Calculate offsets using trigonometry
            double offsetX = radius * Math.cos(angle);
            double offsetY = Beapi.randomDouble();
            double offsetZ = radius * Math.sin(angle);

            double velocity = 0.12; // Adjust the velocity as needed
            double motionAngle = angle + Math.PI / 2; // Perpendicular angle for circular motion

            double particleMotionX = velocity * Math.cos(motionAngle);
            double particleMotionY = motionY; // Keep the same Y motion
            double particleMotionZ = velocity * Math.sin(motionAngle);

            GenericParticleData data = new GenericParticleData(ModParticleTypes.WIND.get());
            data.setLife(1);
            data.setSize(1.5f);
            data.setMotion(particleMotionX, particleMotionY, particleMotionZ);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.3 + offsetY, posZ + offsetZ);
        }
    }
}
