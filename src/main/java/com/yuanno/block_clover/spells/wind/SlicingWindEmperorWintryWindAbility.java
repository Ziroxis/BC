package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.api.ability.interfaces.IShootAbility;
import com.yuanno.block_clover.entities.projectiles.wind.SlicingWindEmperorWintryWindProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class SlicingWindEmperorWintryWindAbility extends Ability implements IShootAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Slicing Wind Emperor Wintry Wind", AbilityCategories.AbilityCategory.ATTRIBUTE, SlicingWindEmperorWintryWindAbility.class)
            .setDescription("Shoots a sword-like projectile made of wind")
            .build();

    public SlicingWindEmperorWintryWindAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(12);
        this.setmanaCost(25);
        this.setExperiencePoint(30);
    }
    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new SlicingWindEmperorWintryWindProjectile(player.level, player);
    }
}
