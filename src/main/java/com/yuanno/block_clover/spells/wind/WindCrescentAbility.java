package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.*;
import com.yuanno.block_clover.api.ability.interfaces.IShootAbility;
import com.yuanno.block_clover.entities.projectiles.wind.WindCrescentProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindCrescentAbility extends Ability implements IShootAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Crescent", AbilityCategories.AbilityCategory.ATTRIBUTE, WindCrescentAbility.class)
            .setDescription("Shoots a crescent made from wind infused with mana.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public WindCrescentAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(7);
        this.setmanaCost(25);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(15);
        this.setEvolutionCost(50);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WindCrescentProjectile(player.level, player);
    }
}
