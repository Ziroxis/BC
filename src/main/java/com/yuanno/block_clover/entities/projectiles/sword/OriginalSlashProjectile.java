package com.yuanno.block_clover.entities.projectiles.sword;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

public class OriginalSlashProjectile extends AbilityProjectileEntity {

    public OriginalSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public OriginalSlashProjectile(World world, LivingEntity player)
    {
        super(SwordProjectiles.ORIGINAL_SLASH.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(32);
        this.setPhysical(false);
    }
}
