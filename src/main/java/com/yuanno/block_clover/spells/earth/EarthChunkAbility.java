package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.earth.EarthChunkProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class EarthChunkAbility extends Ability {
    public static final Ability INSTANCE = new EarthChunkAbility();

    public EarthChunkAbility()
    {
        super("Earth chunk", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a chunk of earth");
        this.setMaxCooldown(5);
        this.setmanaCost(5);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        EarthChunkProjectile projectile = new EarthChunkProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
        return true;
    }

}
