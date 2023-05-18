package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.wind.WindCrescentProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WindCrescentAbility extends Ability {
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
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        WindCrescentProjectile projectile = new WindCrescentProjectile(player.level, player);
        if (this.isEvolved())
        {
            projectile.setDamage(14);
            projectile.setArmorPiercing();
            projectile.setPassThroughEntities();
        }
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
