package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.wind.WindBladeProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindBladeShowerAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Blade Shower", AbilityCategories.AbilityCategory.ATTRIBUTE, WindBladeShowerAbility.class)
            .setDescription("Shoots a big amount of wind blades.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WindBladeShowerAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(20);
        this.setMaxRepeaterCount(5, 5);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WindBladeProjectile projectile = new WindBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 0.5f);

        return true;
    }
}
