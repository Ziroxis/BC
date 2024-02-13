package com.yuanno.block_clover.spells.devil;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallDarkProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class DarkFireBallAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Dark Fire Ball", AbilityCategories.AbilityCategory.DEVIL, DarkFireBallAbility.class)
            .setDescription("Shoots a ball of dark flames")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public DarkFireBallAbility() {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setDevil(true);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        FireBallDarkProjectile projectile = new FireBallDarkProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}