package com.yuanno.block_clover.spells.devil;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.ice.DarkIceBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class DarkIceAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Dark Ice Ball", AbilityCategories.AbilityCategory.DEVIL, DarkIceAbility.class)
            .setDescription("Shoots a ball of dark ice")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public DarkIceAbility() {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setDevil(true);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        DarkIceBallProjectile projectile = new DarkIceBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}