package com.yuanno.block_clover.spells.mercury;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.mercury.MercuryBulletProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class MercuryBulletBarrageAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mercury bullet", AbilityCategories.AbilityCategory.ATTRIBUTE, MercuryBulletBarrageAbility.class)
            .setDescription("Shoots multiple amounts of mercury")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public MercuryBulletBarrageAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(15);
        this.setMaxRepeaterCount(15, 4);
        this.setExperiencePoint(30);
        this.setExperienceGainLevelCap(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        MercuryBulletProjectile projectile = new MercuryBulletProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 20);

        return true;
    }
}
