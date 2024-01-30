package com.yuanno.block_clover.challenge.arena;

import com.yuanno.block_clover.api.challenges.ArenaStyle;
import com.yuanno.block_clover.api.challenges.InProgressChallenge;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.init.ModEntities;
import net.minecraft.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;

public class WalgnerDevilRitualArena extends DevilRitualArena {
    public static final WalgnerDevilRitualArena INSTANCE = new WalgnerDevilRitualArena();

    public WalgnerDevilRitualArena() {
        super();
    }

    @Override
    public Set<LivingEntity> spawnEnemies(InProgressChallenge challenge) {

        Set<LivingEntity> set = new HashSet<>();

        WalgnerDevilEntity boss = new WalgnerDevilEntity(ModEntities.WALGNER_DEVIL.get(), challenge.getShard());

        boss.forcedLoading = true;
        challenge.getShard().addFreshEntity(boss);
        boss.teleportTo(challenge.getArenaPos().getX() - 15, challenge.getArenaPos().getY() - 23, challenge.getArenaPos().getZ() - 15);
        boss.setYBodyRot(-45);
        boss.setTarget(challenge.getOwner());
        //GoalUtil.lookAtEntity(boss, challenge.getOwner());
        set.add(boss);

        return set;
    }
}