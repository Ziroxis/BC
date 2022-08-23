package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.water.WaterDragonProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WaterDragonAbility extends Ability {

    public static final Ability INSTANCE = new WaterDragonAbility();

    public WaterDragonAbility()
    {
        super("Water Dragon", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a dragon made out of water");
        this.setMaxCooldown(15);
        this.setmanaCost(35);
        this.setExperiencePoint(35);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WaterDragonProjectile projectile = new WaterDragonProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}
