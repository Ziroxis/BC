package com.yuanno.block_clover.particles.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GlovesParticleEffect extends ParticleEffect {


    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
    {

        for (int i = 0; i < 20; i++)
        {
            double offsetX = Beapi.randomDouble() / 1.2;
            double offsetY = Beapi.randomDouble();
            double offsetZ = Beapi.randomDouble() / 1.2;

            GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHTNING.get());
            data.setLife(10);
            data.setSize(1.5f);
            data.setColor(1, 1, 1, 0.6f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
        }
    }
}
