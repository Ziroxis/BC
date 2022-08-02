package com.yuanno.block_clover.particles.light;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModParticleTypes;
import com.yuanno.block_clover.particles.GenericParticleData;
import com.yuanno.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightMovementParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 20; i++)
        {
            double offsetX = Beapi.randomDouble() * 2;
            double offsetY = Beapi.randomDouble() * 2;
            double offsetZ = Beapi.randomDouble() * 2;

            GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHT.get());
            data.setLife(2);
            data.setSize(3f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
        }
    }
}
