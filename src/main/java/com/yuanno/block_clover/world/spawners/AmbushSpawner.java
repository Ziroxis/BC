package com.yuanno.block_clover.world.spawners;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AmbushSpawner {
    private Random random = new Random();
    private int cooldown;

    public void tick(ServerWorld world)
    {
        world.getProfiler().push("ambushSpawnerTick");
        if(--this.cooldown <= 0)
        {
            this.cooldown = 1000;
            if (this.random.nextInt(100) <= 23)
                this.spawn(world);
        }
        world.getProfiler().pop();
    }

    public void spawn(ServerWorld world)
    {
        world.getProfiler().push("ambushSpawnerSpawn");
        PlayerEntity player = world.getRandomPlayer();
        if (player == null)
            return;
        else
        {
            IEntityStats entityStats = EntityStatsCapability.get(player);

            if (entityStats.getLevel() < 15)
                return;

            BlockPos targetPos = player.blockPosition();
            boolean canSpawnInBiome = world.getBiome(targetPos).getBiomeCategory() != Biome.Category.OCEAN;
            boolean canSeeSky = player.level.canSeeSky(targetPos);
            if (!canSpawnInBiome || canSeeSky)
                return;
            EntityType banditEntity = ModEntities.BANDIT.get();
            for (int i = 0; i < 5; i++)
            {
                BlockPos spawnPos = Beapi.findOnGroundSpawnLocation(world, banditEntity, targetPos, 10);
                if (spawnPos != null)
                    banditEntity.spawn(world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);
            }

            ITextComponent message = new StringTextComponent("You're surrounded and have no escape, surrender now!");

            player.sendMessage(message, Util.NIL_UUID);
            System.out.println("Ambush at: " + targetPos);
        }
        world.getProfiler().pop();
    }
}
