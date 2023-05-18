package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.wind.PiercingTornadoProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class TornadoPiercingAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Tornado Bullet", AbilityCategories.AbilityCategory.ATTRIBUTE, TornadoPiercingAbility.class)
            .setDescription("Shoots a tornado bullet that pushes away enemies.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public TornadoPiercingAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(20);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        PiercingTornadoProjectile projectile = new PiercingTornadoProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
