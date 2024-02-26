package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.water.TaintedCulvertWyrmProjectile;
import com.yuanno.block_clover.entities.projectiles.water.WaterDragonProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class TaintedCulvertWyrmAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Tainted Culvert Wyrm", AbilityCategories.AbilityCategory.ATTRIBUTE, TaintedCulvertWyrmAbility.class)
            .setDescription("Shoots a a Wyrm, making a water bubble around the player hit.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public TaintedCulvertWyrmAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(1);
        this.setmanaCost(0);
        this.setEvolutionCost(100);
        this.setExperiencePoint(35);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        TaintedCulvertWyrmProjectile projectile = new TaintedCulvertWyrmProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}
