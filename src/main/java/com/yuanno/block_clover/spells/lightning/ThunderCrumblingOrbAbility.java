package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.lightning.GiantLightningOrbProjectile;
import com.yuanno.block_clover.entities.projectiles.lightning.LightningOrbProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class ThunderCrumblingOrbAbility extends Ability {


    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder Crumbling orb", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderCrumblingOrbAbility.class)
            .setDescription("Shoot a ball of electricity.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(ThunderGodGlovesAbility.INSTANCE)
            .build();
    public ThunderCrumblingOrbAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
        this.setEvolutionCost(50);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {

        if (!this.isEvolved())
        {
            LightningOrbProjectile projectile = new LightningOrbProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
        }
        else
        {
            GiantLightningOrbProjectile projectile = new GiantLightningOrbProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5f, 1);
        }
        return true;
    }
}
