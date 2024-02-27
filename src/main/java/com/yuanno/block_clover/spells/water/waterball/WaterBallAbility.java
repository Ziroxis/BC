package com.yuanno.block_clover.spells.water.waterball;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.water.WaterBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class WaterBallAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterBallAbility.class)
            .setDescription("Shoots a water ball imbued with mana.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WaterBallAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;

    }
    private boolean onUseEvent (PlayerEntity player)
    {
        WaterBallProjectile projectile = new WaterBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);

        return true;
    }
}
