package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class DeathScytheAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Death Scythe", AbilityCategories.AbilityCategory.ATTRIBUTE, DeathScytheAbility.class)
            .setDescription("Shoots a scythe of death.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(SlashBladesAbility.INSTANCE)
            .build();
    public DeathScytheAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(5);
        this.setExperiencePoint(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1.5f);
        return true;
    }
}
