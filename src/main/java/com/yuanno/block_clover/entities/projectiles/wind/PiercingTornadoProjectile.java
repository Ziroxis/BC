package com.yuanno.block_clover.entities.projectiles.wind;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PiercingTornadoProjectile extends AbilityProjectileEntity {
    int knockback;

    public PiercingTornadoProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public PiercingTornadoProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.PIERCING_TORNADO.get(), world, player);
        this.setDamage(3);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onBlockImpactEvent = this::onHitBlock;
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onHitBlock(BlockPos hit)
    {
        World world = this.level;

        if (world.getBlockState(hit).getBlock().equals(Blocks.GRASS.getBlock()))
            world.setBlock(hit, Blocks.AIR.defaultBlockState(), 2);

    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        knockback = 3;
        Vector3d vector3d = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale((double)this.knockback);
        entity.push(vector3d.x, 0.1, vector3d.z);

    }
}
