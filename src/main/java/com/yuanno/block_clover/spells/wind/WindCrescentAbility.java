package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.wind.WindCrescentProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindCrescentAbility extends Ability {
    public static final Ability INSTANCE = new WindCrescentAbility();

    public WindCrescentAbility()
    {
        super("Wind Crescent", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a crescent made from wind infused with mana");
        this.setMaxCooldown(7);
        this.setmanaCost(15);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(15);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WindCrescentProjectile projectile = new WindCrescentProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
