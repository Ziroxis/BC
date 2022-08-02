package com.yuanno.block_clover.particles.wind;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WindFlightParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 10; i++)
        {
            /*
            double offsetX = Beapi.randomDouble() / 1.25;
            double offsetY = Beapi.randomDouble() / 1.25;
            double offsetZ = Beapi.randomDouble() / 1.25;
             */
            double offsetX = Beapi.randomDouble()/4;
            double offsetY = Beapi.randomDouble();
            double offsetZ = Beapi.randomDouble()/4;

            GenericParticleData data = new GenericParticleData(ModParticleTypes.WIND.get());
            data.setLife(5);
            data.setSize(1.5f);
            data.setMotion(0, -0.03, 0);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.3 + offsetY, posZ + offsetZ);
        }
    }
}
