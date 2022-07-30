package com.example.block_clover.particles.light;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.init.ModParticleTypes;
import com.example.block_clover.particles.GenericParticleData;
import com.example.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightSwordParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 20; i++)
        {
            double offsetX = Beapi.randomDouble();
            double offsetY = Beapi.randomDouble();
            double offsetZ = Beapi.randomDouble();

            GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHT.get());
            data.setLife(2);
            data.setSize(1f);
            data.setMotion(0, 0.01, 0);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.75 + offsetY, posZ + offsetZ);
        }
    }
}
