package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;

public class SlashManaZone extends ContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Slash mana zone", AbilityCategories.AbilityCategory.ATTRIBUTE, SlashManaZone.class)
            .setDescription("Throws a multitude of slashes around you in a zone.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public SlashManaZone()
    {
        super(INSTANCE);
        this.setMaxCooldown(0);
        this.setmanaCost(0);
        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer) {
        int randX = random.nextInt(8);
        int randY = random.nextInt(2);
        int randZ = random.nextInt(8);

        boolean x = BeJavapi.RNGboolean();
        boolean y = BeJavapi.RNGboolean();

        for (int i = 0; i < 4; i++) {
            DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
            projectile.setDamage(12);

            double posX = player.getX() + (x ? randX : -randX);
            double posY = player.getY() + randY;
            double posZ = player.getZ() + (y ? randZ : -randZ);

            projectile.setPos(posX, posY, posZ);
            projectile.push((projectile.getX() - player.getX()) * 0.3, (projectile.getY() - player.getY()) * 0.1, (projectile.getZ() - player.getZ()) * 0.3);

            player.level.addFreshEntity(projectile);
        }

        if (timer >= 480) {
            this.endContinuity(player);
        }
    }


}
