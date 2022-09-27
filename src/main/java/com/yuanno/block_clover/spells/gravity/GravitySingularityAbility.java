package com.yuanno.block_clover.spells.gravity;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.gravity.SingularityProjectile;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class GravitySingularityAbility extends Ability {

    public static final GravitySingularityAbility INSTANCE = new GravitySingularityAbility();

    public GravitySingularityAbility()
    {
        super("Gravity singularity", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a gravity ball. Sucking up nearby blocks.");
        this.setMaxCooldown(0);
        this.setmanaCost(0);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        SingularityProjectile projectile = new SingularityProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.7f, 1.5f);
        return true;

    }
}
