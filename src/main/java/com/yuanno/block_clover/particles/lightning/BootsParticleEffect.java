package com.yuanno.block_clover.particles.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BootsParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 20; i++)
        {
            /*
            double offsetX = Beapi.randomDouble() / 1.25;
            double offsetY = Beapi.randomDouble() / 1.25;
            double offsetZ = Beapi.randomDouble() / 1.25;
             */
            double offsetX = Beapi.randomDouble() / 3;
            double offsetY = Beapi.randomDouble() / 3;
            double offsetZ = Beapi.randomDouble() / 3;

            GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHTNING.get());
            data.setLife(1);
            data.setSize(1.5f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
        }
    }
}
