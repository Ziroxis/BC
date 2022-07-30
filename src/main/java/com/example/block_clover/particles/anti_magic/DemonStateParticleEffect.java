package com.example.block_clover.particles.anti_magic;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.init.ModParticleTypes;
import com.example.block_clover.particles.GenericParticleData;
import com.example.block_clover.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DemonStateParticleEffect extends ParticleEffect {
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < 3; i++)
        {
            double offsetX = Beapi.randomDouble();
            double offsetY = Beapi.randomDouble();
            double offsetZ = Beapi.randomDouble();


            GenericParticleData data = new GenericParticleData(ModParticleTypes.ANTI_MAGIC.get());
            data.setLife(15);
            data.setMotion(0, 0.03, 0);
            data.setSize(1.5f);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1.8 + offsetY, posZ + offsetZ);

        }
    }
}
