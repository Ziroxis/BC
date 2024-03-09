package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.*;
import com.yuanno.block_clover.api.ability.interfaces.IShootAbility;
import com.yuanno.block_clover.entities.projectiles.wind.WindBladeProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindBladeAbility extends Ability implements IShootAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Blade", AbilityCategories.AbilityCategory.ATTRIBUTE, WindBladeAbility.class)
            .setDescription("Shoots a wind blade.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WindBladeAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(15);
        this.setEvolutionCost(50);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(10);
    }


    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WindBladeProjectile(player.level, player);
    }
}
