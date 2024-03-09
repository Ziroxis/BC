package com.yuanno.block_clover.api.ability.interfaces;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IShootAbility {

    default void onUse(PlayerEntity player, Ability ability)
    {
        AbilityProjectileEntity projectileEntity = getProjectile(player);
        if (projectileEntity != null)
        {
            player.level.addFreshEntity(projectileEntity);
            projectileEntity.shootFromRotation(player, player.xRot, player.yRot, 0, getVelocity(), getInnacuracy());
        }
    }

    default AbilityProjectileEntity getProjectile(PlayerEntity player)
    {
        return null;
    }

    default float getVelocity()
    {
        return 1f;
    }

    default float getInnacuracy()
    {
        return 1f;
    }
}
