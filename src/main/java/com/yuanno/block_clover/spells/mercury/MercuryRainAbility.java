package com.yuanno.block_clover.spells.mercury;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.projectiles.mercury.MercuryRainProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class MercuryRainAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mercury rain", AbilityCategories.AbilityCategory.ATTRIBUTE, MercuryRainAbility.class)
            .setDescription("Makes it rain mercury from above")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();


    double spawnRange = 30; // adjust this value to set the spawn range
    public MercuryRainAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(20);
        this.setmanaCost(10);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(40);
        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        double spawnHeight = player.getY() + 20; // adjust this value to set the spawn height
        Random RANDOM = new Random();
        double spawnRange = 30; // adjust this value to set the spawn range
        for (int i = 0; i < 5; i++)
        {
            double x = player.getX() + (RANDOM.nextGaussian() * spawnRange);
            double z = player.getZ() + (RANDOM.nextGaussian() * spawnRange);
            Vector3d spawnPos = new Vector3d(x, spawnHeight, z);
            MercuryRainProjectile projectile = new MercuryRainProjectile(player.level, player);
            projectile.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
            projectile.setDeltaMovement(0, -1, 0);
            player.level.addFreshEntity(projectile);

        }

    }
}
