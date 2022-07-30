package com.example.block_clover.particles.earth;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.init.ModParticleTypes;
import com.example.block_clover.particles.GenericParticleData;
import com.example.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EarthQuakeParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i<40; i++)
        {
            double offsetX = Beapi.randomDouble() * 2;
            double offsetY = Beapi.randomDouble()/ 3;
            double offsetZ = Beapi.randomDouble() * 2;

            GenericParticleData data = new GenericParticleData(ModParticleTypes.EARTH.get());
            data.setLife(50);
            data.setMotion(0, 0, 0);
            data.setSize(3f);
            int radius = 10;
            for (double x = posX - radius; x <= posX + radius; x++)
            {
                for (double z = posZ - radius; z <= posZ + radius; z++)
                {
                    double distance = ((posX - x) * (posX - x) + ((posZ - z) * (posZ -z)));
                    if (distance < radius);
                    {
                        Beapi.spawnParticles(data, (ServerWorld) world, x + offsetX, posY + offsetY, z + offsetZ);

                    }
                }
            }
            /*
            for (int a = 0; i<20;i++)
            {
                for (int b = 0; b<20;b++)
                {
                    Beapi.spawnParticles(data, (ServerWorld) world, posX + a + offsetX, posY + offsetY, posZ + b + offsetZ);
                }
            }

             */
        }
    }
}
