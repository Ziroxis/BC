package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.sealing.SealingProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class SealingProjectileAbility extends Ability {
    public static final Ability INSTANCE = new SealingProjectileAbility();

    public SealingProjectileAbility()
    {
        super("Sealing Projectile", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Sends a magic projectile sealing the first enemy hit.");
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        SealingProjectile projectile = new SealingProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}
