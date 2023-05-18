package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.entities.projectiles.water.PointBlankDragonProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class PointBlankDragonAbility extends PunchAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Point Blank Dragon", AbilityCategories.AbilityCategory.ATTRIBUTE, PointBlankDragonAbility.class)
            .setDescription("Hit an enemy in range to make water dragon appear dealing massive damage.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public PointBlankDragonAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(30);
        this.setmanaCost(50);
        this.setExperiencePoint(0);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-40);
        stats.alterExperience(+40);
        PointBlankDragonProjectile projectile = new PointBlankDragonProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.1f, 1);

        return 2;
    }
}
