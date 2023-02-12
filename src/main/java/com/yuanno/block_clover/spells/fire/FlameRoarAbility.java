package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.fire.FlameRoarProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class FlameRoarAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Flame roar", AbilityCategories.AbilityCategory.ATTRIBUTE, FlameRoarAbility.class)
            .setDescription("Shoots roar of fire")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public FlameRoarAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(15);
        this.setExperiencePoint(15);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        FlameRoarProjectile projectile = new FlameRoarProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);

        return true;
    }
}
