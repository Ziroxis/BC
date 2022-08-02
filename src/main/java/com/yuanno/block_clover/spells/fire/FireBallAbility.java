package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class FireBallAbility extends Ability {
    public static final Ability INSTANCE = new FireBallAbility();

    public FireBallAbility()
    {
        super("Fire Ball", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a ball of flame");
        this.setMaxCooldown(5);
        this.setmanaCost(5);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        FireBallProjectile projectile = new FireBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}
