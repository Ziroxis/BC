package com.example.block_clover.spells.fire;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.entities.projectiles.fire.FlameRoarProjectile;
import com.example.block_clover.entities.projectiles.wind.WindBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class FlameRoarAbility extends Ability {
    public static final Ability INSTANCE = new FlameRoarAbility();

    public FlameRoarAbility()
    {
        super("Flame Roar", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots roar of fire");
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
