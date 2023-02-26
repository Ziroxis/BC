package com.yuanno.block_clover.spells.darkness;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.darkness.AvidyaSlashProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class AvidyaWildSlashAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Avidya wild slash", AbilityCategories.AbilityCategory.ATTRIBUTE, AvidyaWildSlashAbility.class)
            .setDescription("Wildly slashes darkness in front of you")
            .setDamageKind(AbilityDamageKind.SLASH)
            .setDependencies(DarkCloakedBladeAbility.INSTANCE)
            .build();

    public AvidyaWildSlashAbility()
    {
        super(INSTANCE);
        this.setmanaCost(20);
        this.setMaxCooldown(15);
        this.setMaxRepeaterCount(6, 2);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {

            AvidyaSlashProjectile projectile = new AvidyaSlashProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 10);
        return false;

    }
}
