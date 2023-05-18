package com.yuanno.block_clover.particles.sealing;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SealingParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 20; i++)
        {

            double offsetX = Beapi.randomDouble();
            double offsetY = Beapi.randomDouble();
            double offsetZ = Beapi.randomDouble();

            GenericParticleData data = new GenericParticleData(ModParticleTypes.SEALING.get());
            data.setLife(1);
            data.setSize(1.5f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.3 + offsetY, posZ + offsetZ);
        }
    }
}
