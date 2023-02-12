package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.earth.EarthChunkProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class EarthChunkAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Eartch chunk", AbilityCategories.AbilityCategory.ATTRIBUTE, EarthChunkAbility.class)
            .setDescription("Shoots a chunk of earth")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public EarthChunkAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(4);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
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
