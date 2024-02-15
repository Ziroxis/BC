package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.fire.SpiralFlameProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class SpiralFlameAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Spiral flame", AbilityCategories.AbilityCategory.ATTRIBUTE, SpiralFlameAbility.class)
            .setDescription("Shoots a spiral of flames")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public SpiralFlameAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(15);
        this.setEvolutionCost(30);
        this.setExperiencePoint(15);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        SpiralFlameProjectile projectile = new SpiralFlameProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);

        return true;
    }
}
