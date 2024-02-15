package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.wind.WindBladeProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindBladeAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Blade", AbilityCategories.AbilityCategory.ATTRIBUTE, WindBladeAbility.class)
            .setDescription("Shoots a wind blade.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WindBladeAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(15);
        this.setEvolutionCost(50);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WindBladeProjectile projectile = new WindBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
