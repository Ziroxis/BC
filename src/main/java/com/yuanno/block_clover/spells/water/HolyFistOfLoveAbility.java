package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.entities.projectiles.water.HolyFistOfLoveProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class HolyFistOfLoveAbility extends PunchAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Holy Fist of Love", AbilityCategories.AbilityCategory.ATTRIBUTE, HolyFistOfLoveAbility.class)
            .setDescription("The user firmly create a water hand from above and smash the targeted opponent dealing decent damage")
            .setDamageKind(AbilityDamageKind.BLUNT)
            .build();

    public HolyFistOfLoveAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setExperiencePoint(30);
        this.setExperienceGainLevelCap(50);
        this.onHitEntityEvent = this::onHitEntityEvent;

    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        HolyFistOfLoveProjectile holyFistOfLoveProjectile = new HolyFistOfLoveProjectile(player.level, player);
        player.level.addFreshEntity(holyFistOfLoveProjectile);
        holyFistOfLoveProjectile.setPos(target.getX(), target.getY() + 4, target.getZ());
        holyFistOfLoveProjectile.shoot(0, -180, 0, 1f, 0);

        return 2;
    }
}
