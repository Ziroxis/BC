package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.water.WaterDragonProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WaterDragonAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Dragon", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterDragonAbility.class)
            .setDescription("Shoots a dragon made out of water.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WaterDragonAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(20);
        this.setmanaCost(50);
        this.setEvolvedManaCost(35);
        this.setEvolutionCost(100);
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
