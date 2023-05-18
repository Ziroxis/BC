package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.water.WaterBallProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WaterBallAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterBallAbility.class)
            .setDescription("Shoots a water ball imbued with mana.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WaterBallAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setEvolutionCost(50);
        this.setEvolvedManaCost(5);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (this.isEvolved())
        {
            WaterBallProjectile projectile = new WaterBallProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);

            int tickCount = player.tickCount + 60;
            if (tickCount < player.tickCount) {
                WaterBallProjectile projectile1 = new WaterBallProjectile(player.level, player);
                player.level.addFreshEntity(projectile1);
                projectile1.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);
            }
            int tickCountAgain = player.tickCount + 120;
            if (tickCountAgain < player.tickCount) {
                WaterBallProjectile projectile2 = new WaterBallProjectile(player.level, player);
                player.level.addFreshEntity(projectile2);
                projectile2.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);
            }
        }
        else
        {
            WaterBallProjectile projectile = new WaterBallProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);
        }
        return true;
    }
}
