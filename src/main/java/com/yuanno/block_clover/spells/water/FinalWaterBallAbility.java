package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.water.WaterBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class FinalWaterBallAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Final Water Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, FinalWaterBallAbility.class)
            .setDescription("Shoots 5 water ball imbued with mana.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public FinalWaterBallAbility() {
        super(INSTANCE);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.setMaxRepeaterCount(5, 2);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player) {

        WaterBallProjectile projectile = new WaterBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.2f, 1);

        return true;
    }
}
