package com.yuanno.block_clover.spells.ice;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.ice.IceBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class IceBallAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Ice Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, IceBallAbility.class)
            .setDescription("Shoots a ball of ice")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public IceBallAbility() {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        IceBallProjectile projectile = new IceBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }
}