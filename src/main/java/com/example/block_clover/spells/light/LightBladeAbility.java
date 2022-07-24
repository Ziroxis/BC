package com.example.block_clover.spells.light;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.example.block_clover.entities.projectiles.wind.WindBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class LightBladeAbility extends Ability {
    public static final LightBladeAbility INSTANCE = new LightBladeAbility();

    public LightBladeAbility()
    {
        super("Light Blade", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setmanaCost(10);
        this.setCooldown(2);
        this.setExperiencePoint(5);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        LightBladeProjectile projectile = new LightBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 4f, 1);

        return true;

    }
}
