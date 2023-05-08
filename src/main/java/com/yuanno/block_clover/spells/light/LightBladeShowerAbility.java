package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.light.GiantLightBladeProjectile;
import com.yuanno.block_clover.entities.projectiles.light.LightBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class LightBladeShowerAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Light blade shower", AbilityCategories.AbilityCategory.ATTRIBUTE, LightBladeShowerAbility.class)
            .setDescription("Shoots a big amount of light blades")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public LightBladeShowerAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(12);
        this.setmanaCost(25);
        this.setExperiencePoint(25);
        this.setMaxRepeaterCount(10, 4);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (this.isEvolved())
        {
            GiantLightBladeProjectile projectile = new GiantLightBladeProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 4f, 1);
            return true;
        }
        LightBladeProjectile projectile = new LightBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 4f, 1);
        return true;
    }
}
