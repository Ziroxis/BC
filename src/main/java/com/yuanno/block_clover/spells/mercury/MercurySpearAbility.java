package com.yuanno.block_clover.spells.mercury;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.projectiles.mercury.MercuryBulletProjectile;
import com.yuanno.block_clover.entities.projectiles.mercury.MercurySpearProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class MercurySpearAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mercury Spear", AbilityCategories.AbilityCategory.ATTRIBUTE, MercurySpearAbility.class)
            .setDescription("Launches a spear of mercury, poisoning the enemy hit")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public MercurySpearAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setEvolvedManaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        MercurySpearProjectile projectile = new MercurySpearProjectile(player.level, player);
        if (this.isEvolved()) {
            projectile.setDamage(14);
            projectile.setArmorPiercing();
            projectile.setPassThroughEntities();
        }
        player.level.addFreshEntity(projectile);
        if (!this.isEvolved())
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);
        else
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
        return true;
    }
}
