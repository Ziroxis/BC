package com.yuanno.block_clover.spells.water.waterball;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.water.WaterBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class EvolvedWaterBallAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Evolved Water Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, EvolvedWaterBallAbility.class)
            .setDescription("Shoots 3 water ball imbued with mana.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public EvolvedWaterBallAbility()
    {
        super(INSTANCE);
        this.setEvolvedAbility(FinalWaterBallAbility.INSTANCE);
        this.setEvolutionCost(3);
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setMaxRepeaterCount(3, 3);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {

        WaterBallProjectile projectile = new WaterBallProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.2f, 1);

        return true;
    }
}
