package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.light.GiantLightBladeProjectile;
import com.yuanno.block_clover.entities.projectiles.light.LightBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class LightBladeAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Light blade", AbilityCategories.AbilityCategory.ATTRIBUTE, LightBladeAbility.class)
            .setDescription("Shoots a blade made out of light")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public LightBladeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(10);
        this.setCooldown(2);
        this.setEvolutionCost(50);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        LightBladeProjectile projectile = new LightBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3f, 1);
        return true;

    }
}
