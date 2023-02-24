package com.yuanno.block_clover.spells.mercury;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.mercury.MercuryBulletProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class MercuryBulletAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mercury Bullet", AbilityCategories.AbilityCategory.ATTRIBUTE, MercuryBulletAbility.class)
            .setDescription("Shoots a ball of mercury, poisoning the target")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public MercuryBulletAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        MercuryBulletProjectile projectile = new MercuryBulletProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);
        return true;
    }
}
