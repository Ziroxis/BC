package com.example.block_clover.particles.earth;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.init.ModParticleTypes;
import com.example.block_clover.particles.GenericParticleData;
import com.example.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EarthChargeParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {

        for (int i = 0; i<10; i++)
        {
            double offsetX = Beapi.randomDouble()/ 2;
            double offsetY = Beapi.randomDouble()/ 4;
            double offsetZ = Beapi.randomDouble()/ 2;
            GenericParticleData data = new GenericParticleData(ModParticleTypes.EARTH.get());
            data.setLife(5);
            data.setMotion(0, 0, 0);
            data.setSize(1f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1.4 + offsetY, posZ + offsetZ);

        }
    }
}
