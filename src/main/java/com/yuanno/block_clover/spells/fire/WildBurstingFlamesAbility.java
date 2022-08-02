package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WildBurstingFlamesAbility extends RepeaterAbility {

    public static final WildBurstingFlamesAbility INSTANCE = new WildBurstingFlamesAbility();

    public WildBurstingFlamesAbility()
    {
        super("Wild Bursting Flames", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a big amount of balls of flames.");
        this.setMaxCooldown(15);
        this.setmanaCost(20);
        this.setMaxRepeaterCount(15, 4);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        FireBallProjectile projectile = new FireBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 20);

        return true;
    }
}
