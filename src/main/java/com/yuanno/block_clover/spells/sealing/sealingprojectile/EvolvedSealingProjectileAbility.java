package com.yuanno.block_clover.spells.sealing.sealingprojectile;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.sealing.EvolvedSealingProjectile;
import com.yuanno.block_clover.entities.projectiles.sealing.SealingProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class EvolvedSealingProjectileAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Evolved Sealing Projectile", AbilityCategories.AbilityCategory.ATTRIBUTE, EvolvedSealingProjectileAbility.class)
            .setDescription("Sends a magic projectile sealing the first enemy hit and sealing entities close.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public EvolvedSealingProjectileAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(4);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        EvolvedSealingProjectile projectile = new EvolvedSealingProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}
