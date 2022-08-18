package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WindGaleProjectile extends AbilityProjectileEntity {

    int knockback;

    public WindGaleProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WindGaleProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.WIND_GALE.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onBlockImpactEvent = this::onHitBlock;
    }

    private void onHitBlock(BlockPos hit)
    {
        World world = this.level;

        if (world.getBlockState(hit).getBlock().equals(Blocks.GRASS.getBlock()))
            world.setBlock(hit, Blocks.AIR.defaultBlockState(), 2);

    }


}
