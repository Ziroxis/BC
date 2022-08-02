package com.yuanno.block_clover.particles.anti_magic;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlackTornadoParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        double t = 0;
        double x, z;
        Random rand = world.random;
        double size = 2;
        double offsetX = Beapi.randomDouble() / 2;
        double offsetY = Beapi.randomDouble();
        double offsetZ = Beapi.randomDouble() / 2;

        while (t < 1)
        {
            t += 0.5 * Math.PI;

            for (double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
            {
                x = t * Math.cos(theta);
                z = t * Math.sin(theta);

                motionX = Math.sin(theta) / 10;
                motionY = 0.3 + (rand.nextDouble() / 10);
                motionZ = -Math.cos(theta) / 10;
                for (int i = -10; i < 10; i++)
                {
                    GenericParticleData data = new GenericParticleData(ModParticleTypes.ANTI_MAGIC.get());
                    data.setLife(15);
                    data.setMotion(motionX, motionY, motionZ);
                    data.setSize(1.5f);
                    Beapi.spawnParticles(data, (ServerWorld) world, posX + (x * size) + offsetX, posY + 1 + offsetY, posZ + (z * size) +offsetZ);

                }
                    //((ServerWorld) world).sendParticles(ModParticleTypes.ANTI_MAGIC.get(), posX + (x * size) + (Beapi.randomDouble() / 2), posY + i, posZ + (z * size) + (Beapi.randomDouble() / 2), 1, motionX, motionY, motionZ, 0.03);
            }
        }
    }
}
