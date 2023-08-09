package com.yuanno.block_clover.world.random;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ManaSiphoning {
    private Random random = new Random();
    private int cooldown;

    public void tick(ServerWorld serverWorld)
    {
        serverWorld.getProfiler().push("manaSiphoningTick");
        if(--this.cooldown <= 0)
        {
            this.cooldown = 300;
            if (this.random.nextInt(100) <= 25)
                this.siphon(serverWorld);
        }
        serverWorld.getProfiler().pop();
    }

    public void siphon(ServerWorld world)
    {
        world.getProfiler().push("manaSiphoning");
        PlayerEntity player = world.getRandomPlayer();
        if (player == null)
            return;
        else
        {
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (!entityStats.getInnateDevil() || entityStats.getControlledDevilList().contains(entityStats.getDevil()))
                return;

            ITextComponent message = new StringTextComponent("Give me your mana!");
            player.sendMessage(message, Util.NIL_UUID);

            entityStats.setMana(0);
        }
        world.getProfiler().pop();
    }
}
