package com.yuanno.block_clover.particles.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EarthShakeParticleEffect extends ParticleEffect {

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        double phi = 0;
        double x, y, z;
        double radius = 0.4;
        Random random = world.random;
        int i = 0;

        while (phi < 1)
        {
            phi += 0.5 * Math.PI;
            for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 64)
            {

                x = radius * phi * Math.cos(theta);
                y = radius * Beapi.randomDouble() / 2;
                z = radius * phi * Math.sin(theta);


                /* -> more particles on the player himself
                x = (radius * Math.cos(theta) * Math.sin(phi)) + Beapi.randomDouble();
                y = radius * Math.cos(phi) + (Beapi.randomDouble() * 2);
                z = (radius* Math.sin(theta) * Math.sin(phi)) + Beapi.randomDouble();
                 */


                motionX = x * 1.2;
                motionY = 0.2 + (random.nextDouble() / 8);
                motionZ = z * 1.2;

                GenericParticleData data;
                if(i % 3 == 0)
                    data = new GenericParticleData(ModParticleTypes.EARTH.get());
                else
                    data = new GenericParticleData(ModParticleTypes.EARTH.get());
                data.setLife(7);
                data.setSize(3F);
                data.setMotion(motionX, motionY / 2, motionZ);
                //data.setColor(0.7F, 0, 0.7F, 0.5F); -> purple
                data.setColor(1, 1, 1, 1);
                Beapi.spawnParticles(data, (ServerWorld) world, posX + x, posY , posZ + z);

                i++;
            }
        }
    }
}
