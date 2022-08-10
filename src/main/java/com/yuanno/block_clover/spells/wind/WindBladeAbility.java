package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.wind.WindBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindBladeAbility extends Ability {
    public static final Ability INSTANCE = new WindBladeAbility();

    public WindBladeAbility()
    {
        super("Wind Blade", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a wind blade");
        this.setMaxCooldown(3);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WindBladeProjectile projectile = new WindBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
