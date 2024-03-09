package com.yuanno.block_clover.spells.water.waterball;

import com.yuanno.block_clover.api.ability.*;
import com.yuanno.block_clover.api.ability.interfaces.IShootAbility;
import com.yuanno.block_clover.entities.projectiles.water.WaterBallProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class WaterBallAbility extends Ability implements IShootAbility {

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

    }


    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WaterBallProjectile(player.level, player);
    }
}
